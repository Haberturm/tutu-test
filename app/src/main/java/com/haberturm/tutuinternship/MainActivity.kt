package com.haberturm.tutuinternship

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.navigation.compose.rememberNavController
import com.haberturm.tutuinternship.data.network.RetrofitClient
import com.haberturm.tutuinternship.ui.nav.ScreenHolder
import com.haberturm.tutuinternship.ui.theme.TutuInternshipTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            TutuInternshipTheme {
                Scaffold {
                    ScreenHolder(navController, it)
                }
            }
        }
    }
}
