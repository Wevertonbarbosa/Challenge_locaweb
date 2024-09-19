package br.com.fiap.locaweb

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.locaweb.screen.DetailScreen
import br.com.fiap.locaweb.screen.EmailScreen
import br.com.fiap.locaweb.screen.LoginScreen
import br.com.fiap.locaweb.screen.PreferenceScreen
import br.com.fiap.locaweb.screen.SendEmailScreen
import br.com.fiap.locaweb.ui.theme.LocaWebTheme

class MainActivity : ComponentActivity() {
    companion object {
        lateinit var instance: MainActivity
            private set
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instance = this
        setContent {

            val navController = rememberNavController()
            var isDarkTheme by remember { mutableStateOf(loadThemePreference()) }


            LocaWebTheme(darkTheme = isDarkTheme) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = "login"
                    ) {
                        composable(route = "login") {
                            LoginScreen(navController)
                        }
                        composable(route = "email") {
                            EmailScreen(isInternetConnected(), navController)
                        }
                        composable("detail/{id}") { backStackEntry ->
                            val emailId = backStackEntry.arguments?.getString("id")
                            DetailScreen(emailId = emailId, navController = navController)
                        }

                        composable(route = "settings") {
                            PreferenceScreen(
                                navController = navController,
                                isDarkTheme = isDarkTheme,
                                onThemeChanged = { newTheme ->
                                    isDarkTheme = newTheme
                                    saveThemePreference(newTheme)
                                }
                            )
                        }
                        composable(route = "sendEmail") {
                            SendEmailScreen(navController)
                        }
                    }
                }
            }
        }
    }

    private fun isInternetConnected(): Boolean {
        val connectivityManager =
            MainActivity.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val capabilities =
                connectivityManager.getNetworkCapabilities(network) ?: return false

            return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo ?: return false
            return networkInfo.isConnected
        }
    }

    private fun loadThemePreference(): Boolean {
        val prefs = getSharedPreferences("app_prefs", MODE_PRIVATE)
        return prefs.getBoolean("theme", false) // Default to light theme
    }

    private fun saveThemePreference(isDark: Boolean) {
        val prefs = getSharedPreferences("app_prefs", MODE_PRIVATE)
        prefs.edit().putBoolean("theme", isDark).apply()
    }
}
