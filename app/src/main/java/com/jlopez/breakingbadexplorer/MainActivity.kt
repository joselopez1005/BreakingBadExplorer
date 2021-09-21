package com.jlopez.breakingbadexplorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.jlopez.breakingbadexplorer.characterdetailscreen.CharacterDetailScreen
import com.jlopez.breakingbadexplorer.characterlistscreen.CharacterListScreen
import com.jlopez.breakingbadexplorer.ui.theme.BreakingBadExplorerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BreakingBadExplorerTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "actors_list_screen"
                ) {
                    composable("actors_list_screen"){
                        CharacterListScreen(navController = navController)
                    }
                    composable("actors_detail_screen/{id}",
                        arguments = listOf(
                            navArgument("id") {
                                type = NavType.IntType
                            }
                        )
                    ){
                        val id = remember {
                            it.arguments?.getInt("id")
                        }
                        CharacterDetailScreen(
                            id = id?: 0,
                            navController = navController,
                        )

//                    composable("actors_detail_screen/{image}/{name}/{occupation}/{status}/{nickname}/{season}",
//                        arguments = listOf(
//                            navArgument("image") {
//                                type = NavType.StringType
//                            },
//                            navArgument("name") {
//                                type = NavType.StringType
//                            },
//                            navArgument("occupation") {
//                                type = NavType.StringArrayType
//                            },
//                            navArgument("status") {
//                                type = NavType.StringType
//                            },
//                            navArgument("nickname") {
//                                type = NavType.StringType
//                            },
//                            navArgument("season") {
//                                type = NavType.IntArrayType
//                            }
//                        )
//                    ) {
//                        val image = remember {
//                            it.arguments?.getString("image")
//                        }
//                        val name = remember {
//                            it.arguments?.getString("name")
//                        }
//                        val occupation = remember {
//                            it.arguments?.getStringArrayList("occupation")
//                        }
//                        val status = remember {
//                            it.arguments?.getString("status")
//                        }
//                        val nickname = remember {
//                            it.arguments?.getString("nickname")
//                        }
//                        val season = remember {
//                            it.arguments?.getIntegerArrayList("season") ?: ArrayList()
//                        }
//                        CharacterDetailScreen(
//                            image = image?:"",
//                            name= name?:"",
//                            occupation= occupation?: ArrayList<String>(),
//                            status = status?:"",
//                            nickname =  nickname?:"",
//                            season= season?:ArrayList<Int>(),
//                            navController = navController
//                        )

                    }
                }
            }
        }
    }


}
