# Profil développeur Compose

## Description

Cette application Android affiche une fiche « Profil développeur » réalisée avec Jetpack Compose.
L'écran présente le nom, le rôle, une courte description, une liste de compétences, un contact
et la disponibilité d'un développeur. Une zone cliquable de disponibilité et un bouton « Contacter »
signalent les clics au parent via des callbacks, sans modifier l'interface.
Deux previews montrent deux profils différents : un profil Android junior disponible et un profil full-stack non disponible.

## Capture

![Preview de la fiche profil](docs/screenshot.png)

## Notions utilisées

- Fonctions composables (`@Composable`)
- Paramètres et données passées aux composables
- `Modifier` passé par le parent (`modifier: Modifier = Modifier`)
- Chaînage de modifiers (`fillMaxWidth` → `clickable` → `semantics`)
- Padding externe / padding interne
- Callbacks (`onClick: () -> Unit`)
- `Scaffold` et `innerPadding`
- `Surface`, `Column`, `Text`, `Button`
- Previews (`@Preview`)
- Accessibilité de base (`Role.Button`, `onClickLabel`, `contentDescription`)

## Explication

**1. Quels composables avez-vous créés ?**
Dans `ProfileScreen.kt` : `ProfileScreen`, `ProfileCard`, `ProfileHeader`, `SectionTitle`, `SkillItem`,
`AvailabilityBadge`, `ContactButton`, plus deux previews (`ProfileScreenAndroidJuniorPreview` et
`ProfileScreenFullStackPreview`).

**2. Quel composable représente l'écran principal ?**
`ProfileScreen`. Il pose le `Scaffold`, récupère `innerPadding` et place la `ProfileCard`. C'est lui
qu'appelle `MainActivity` dans `setContent`.

**3. Où utilisez-vous un padding externe ?**
Dans `ProfileScreen`, le parent place la carte avec `Modifier.padding(innerPadding).padding(16.dp)`.
Dans `ProfileCard`, le parent espace aussi ses enfants de l'extérieur, par exemple
`SkillItem(modifier = Modifier.padding(bottom = 6.dp))` ou `AvailabilityBadge(modifier = Modifier.padding(top = 16.dp))`.
Le composant enfant ne choisit pas sa position : c'est le parent qui la décide.

**4. Où utilisez-vous un padding interne ?**
Dans les composants eux-mêmes, pour espacer leur contenu de leurs bords : la `Column` de `ProfileCard`
(`padding(20.dp)`), le `Text` de `SkillItem` (`padding(horizontal = 12.dp, vertical = 6.dp)`) et la
`Column` de `AvailabilityBadge` (`padding(16.dp)`).

**5. Quel composant reçoit un callback ?**
`ContactButton` (`onClick`) et `AvailabilityBadge` (`onClick`). `ProfileCard` et `ProfileScreen`
reçoivent `onContactClick` et `onAvailabilityClick` et les transmettent. Les callbacks restent vides
dans `MainActivity` et dans les previews : le composant signale l'événement, il ne décide pas de la logique.

**6. Quelle information d'accessibilité avez-vous ajoutée ?**
`AvailabilityBadge` est une zone cliquable personnalisée (une `Surface`, pas un `Button`). Elle déclare
`role = Role.Button` et un `onClickLabel` (« voir les détails de disponibilité ») dans `clickable`, et une
`contentDescription` via `semantics` (par exemple « Disponible : Disponible pour une alternance dès septembre »).
Un lecteur d'écran annonce donc un bouton, son contenu et l'action. Pour `ContactButton`, le `Button` de
Material fournit déjà le rôle bouton et lit son texte : il est plus explicite qu'une simple zone cliquable.

**7. Pourquoi vos composants peuvent-ils être considérés comme réutilisables ?**
Ils ne contiennent aucune donnée en dur : le nom, le rôle, les compétences, le texte du bouton et les
actions sont passés en paramètres. Chacun accepte un `modifier: Modifier = Modifier` appliqué à son
élément racine, ce qui laisse le parent choisir le placement. La preuve : les deux previews affichent
deux profils différents avec exactement les mêmes composables, et `SectionTitle` sert à la fois pour
« Compétences » et « Contact ».

## Lancer le projet

Ouvrir le dossier dans Android Studio, laisser Gradle se synchroniser, puis lancer la configuration `app`
ou ouvrir `ProfileScreen.kt` en mode Split pour voir les previews.
