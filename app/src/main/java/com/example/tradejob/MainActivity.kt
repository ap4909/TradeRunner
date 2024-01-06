package com.example.tradejob

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.CurrencyExchange
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.CurrencyExchange
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.tradejob.ui.theme.TradeJobTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tabItems = listOf(
            TabItem(
                title = "Trade",
                unselectedIcon = Icons.Outlined.CurrencyExchange,
                selectedIcon = Icons.Filled.CurrencyExchange
            ),
            TabItem(
                title = "Backtest",
                unselectedIcon = Icons.Outlined.Book,
                selectedIcon = Icons.Filled.Book
            )
        )
        setContent {
            TradeJobTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var selectedTabIndex by remember {
                        mutableIntStateOf(0)
                    }
                    if (selectedTabIndex == 0) {
                        androidx.compose.foundation.layout.Column(
                            modifier = androidx.compose.ui.Modifier.fillMaxSize(),
                            verticalArrangement = androidx.compose.foundation.layout.Arrangement.Top,
                        ) {
                            TradeTab()
                        }
                    }

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Bottom,
                    ){

                        TabRow(selectedTabIndex = selectedTabIndex) {
                            tabItems.forEachIndexed{index, item ->
                            Tab(
                                selected = index == selectedTabIndex,
                                onClick = {
                                    selectedTabIndex = index
                                },
                                text = {
                                    Text(text = item.title)
                                },
                                icon = {
                                    Icon(
                                        imageVector = if (index == selectedTabIndex) {
                                            item.selectedIcon
                                        } else item.unselectedIcon,
                                        contentDescription = item.title
                                    )
                                }
                            )

                            }
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun TradeTab(){
    SimpleOutlinedTextFieldSample()
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleOutlinedTextFieldSample() {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("Label") }
    )
}
data class TabItem(
    val title: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector
)