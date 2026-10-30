package com.example.profildeveloppeur

/**
 * Données affichées par la fiche profil.
 * Simple classe Kotlin : aucune logique, aucun état.
 */
data class DeveloperProfile(
    val name: String,
    val role: String,
    val description: String,
    val skills: List<String>,
    val contact: String,
    val availability: String,
    val isAvailable: Boolean,
)

val androidJuniorProfile = DeveloperProfile(
    name = "Camille Martin",
    role = "Développeur·se Android junior",
    description = "Étudiant·e en 3e année à l'ESGI, passionné·e par les interfaces mobiles " +
        "soignées et accessibles. Je construis mes premières applications avec Jetpack Compose.",
    skills = listOf("Kotlin", "Jetpack Compose", "Material 3", "Git"),
    contact = "camille.martin@example.com",
    availability = "Disponible pour une alternance dès septembre",
    isAvailable = true,
)

val fullStackProfile = DeveloperProfile(
    name = "Alex Moreau",
    role = "Développeur·se full-stack",
    description = "Cinq ans d'expérience sur des applications web et mobiles. " +
        "J'aime faire le lien entre le backend, l'API et l'interface utilisateur.",
    skills = listOf("TypeScript", "Node.js", "PostgreSQL", "Kotlin", "Docker"),
    contact = "alex.moreau@example.com",
    availability = "En mission jusqu'en mars, pas disponible actuellement",
    isAvailable = false,
)
