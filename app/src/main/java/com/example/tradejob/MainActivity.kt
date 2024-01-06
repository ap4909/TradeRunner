import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tradejob.ui.theme.TradeJobTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TradeJobTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Set up the navigation
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "tab1") {
                        composable("tab1") { TabContent("Tab 1") }
                        composable("tab2") { TabContent("Tab 2") }
                    }
                    // Set up the bottom navigation
                    BottomNavigation {
                        BottomNavigationItem(
                            icon = { Icon(Icons.Default.Home, contentDescription = null) },
                            label = { Text("Tab 1") },
                            selected = navController.currentDestination?.route == "tab1",
                            onClick = {
                                navController.navigate("tab1") {
                                    popUpTo(navController.graph.startDestinationRoute!!) {
                                        saveState = true
                                    }
                                }
                            }
                        )
                        BottomNavigationItem(
                            icon = { Icon(Icons.Default.Settings, contentDescription = null) },
                            label = { Text("Tab 2") },
                            selected = navController.currentDestination?.route == "tab2",
                            onClick = {
                                navController.navigate("tab2") {
                                    popUpTo(navController.graph.startDestinationRoute!!) {
                                        saveState = true
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TabContent(tabTitle: String) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = tabTitle)
                }
            )
        }
    ) {
        // Content for each tab goes here
        Text(text = "Content for $tabTitle")
    }
}
