package com.example.profildeveloppeur

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.profildeveloppeur.ui.theme.ProfilDeveloppeurTheme

/**
 * Écran principal : pose le Scaffold et place la fiche dans l'écran.
 * Les clics sont remontés au parent via des callbacks.
 */
@Composable
fun ProfileScreen(
    profile: DeveloperProfile,
    onContactClick: () -> Unit,
    onAvailabilityClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(modifier = modifier) { innerPadding ->
        // Padding EXTERNE : c'est l'écran (le parent) qui décide où se place la carte.
        ProfileCard(
            profile = profile,
            onContactClick = onContactClick,
            onAvailabilityClick = onAvailabilityClick,
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp),
        )
    }
}

/**
 * La fiche complète : en-tête, description, compétences, disponibilité et action.
 */
@Composable
fun ProfileCard(
    profile: DeveloperProfile,
    onContactClick: () -> Unit,
    onAvailabilityClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        color = MaterialTheme.colorScheme.surfaceVariant,
    ) {
        // Padding INTERNE : la carte espace son propre contenu de ses bords.
        Column(modifier = Modifier.padding(20.dp)) {
            ProfileHeader(name = profile.name, role = profile.role)

            Text(
                text = profile.description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 12.dp),
            )

            SectionTitle(
                title = "Compétences",
                modifier = Modifier.padding(top = 20.dp, bottom = 8.dp),
            )
            for (skill in profile.skills) {
                SkillItem(
                    skill = skill,
                    modifier = Modifier.padding(bottom = 6.dp),
                )
            }

            SectionTitle(
                title = "Contact",
                modifier = Modifier.padding(top = 20.dp, bottom = 8.dp),
            )
            Text(
                text = profile.contact,
                style = MaterialTheme.typography.bodyMedium,
            )

            AvailabilityBadge(
                availability = profile.availability,
                isAvailable = profile.isAvailable,
                onClick = onAvailabilityClick,
                modifier = Modifier.padding(top = 16.dp),
            )

            ContactButton(
                text = "Contacter ${profile.name}",
                onClick = onContactClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
            )
        }
    }
}

/**
 * Nom et rôle du développeur.
 */
@Composable
fun ProfileHeader(
    name: String,
    role: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = name,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = role,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
        )
    }
}

/**
 * Petit titre de section, réutilisé pour « Compétences » et « Contact ».
 */
@Composable
fun SectionTitle(
    title: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleSmall,
        fontWeight = FontWeight.SemiBold,
        modifier = modifier,
    )
}

/**
 * Une compétence, affichée comme une petite étiquette.
 */
@Composable
fun SkillItem(
    skill: String,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.small,
        color = MaterialTheme.colorScheme.secondaryContainer,
    ) {
        // Padding interne : espace entre le texte et le bord de l'étiquette.
        Text(
            text = skill,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
        )
    }
}

/**
 * Zone cliquable personnalisée indiquant la disponibilité.
 * Ce n'est pas un Button : on doit donc décrire nous-mêmes son rôle
 * et son action pour les lecteurs d'écran.
 */
@Composable
fun AvailabilityBadge(
    availability: String,
    isAvailable: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val status = if (isAvailable) "Disponible" else "Non disponible"

    Surface(
        // Chaîne de modifiers : largeur -> clic (avec rôle bouton) -> description accessible.
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                onClickLabel = "voir les détails de disponibilité",
                role = Role.Button,
                onClick = onClick,
            )
            .semantics {
                contentDescription = "$status : $availability"
            },
        shape = MaterialTheme.shapes.medium,
        color = if (isAvailable) {
            MaterialTheme.colorScheme.primaryContainer
        } else {
            MaterialTheme.colorScheme.errorContainer
        },
    ) {
        // Padding interne, appliqué après le clickable : toute la zone reste cliquable.
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = status,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = availability,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

/**
 * Bouton d'action principal. Il signale le clic via [onClick]
 * sans décider de ce qui se passe ensuite.
 */
@Composable
fun ContactButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
    ) {
        Text(text = text)
    }
}

@Preview(name = "Profil Android junior – disponible", showBackground = true, showSystemUi = true)
@Composable
fun ProfileScreenAndroidJuniorPreview() {
    ProfilDeveloppeurTheme(dynamicColor = false) {
        ProfileScreen(
            profile = androidJuniorProfile,
            onContactClick = {},
            onAvailabilityClick = {},
        )
    }
}

@Preview(name = "Profil full-stack – non disponible", showBackground = true, showSystemUi = true)
@Composable
fun ProfileScreenFullStackPreview() {
    ProfilDeveloppeurTheme(dynamicColor = false) {
        ProfileScreen(
            profile = fullStackProfile,
            onContactClick = {},
            onAvailabilityClick = {},
        )
    }
}
