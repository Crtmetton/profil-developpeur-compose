package com.example.profildeveloppeur

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.profildeveloppeur.ui.theme.ProfilDeveloppeurTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProfilDeveloppeurTheme {
                ProfileScreen(
                    profile = androidJuniorProfile,
                    onContactClick = {
                        // Pas de logique applicative ici : on signale simplement le clic.
                    },
                    onAvailabilityClick = {
                        // Même principe : l'événement remonte, rien ne change à l'écran.
                    },
                )
            }
        }
    }
}
