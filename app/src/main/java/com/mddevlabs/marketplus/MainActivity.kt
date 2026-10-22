package com.mddevlabs.marketplus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mddevlabs.marketplus.presentation.CoinDetailViewmodel
import com.mddevlabs.marketplus.presentation.CoinViewModel
import com.mddevlabs.marketplus.presentation.coinDetailScreen
import com.mddevlabs.marketplus.presentation.coinListIteam
import com.mddevlabs.marketplus.presentation.theme.MarketplusTheme
import com.mddevlabs.marketplus.utils.InternetObserver
import com.mddevlabs.marketplus.utils.isInternetAvailable
import kotlin.getValue

class MainActivity : ComponentActivity() {


    private  val viewModel: CoinViewModel by  viewModels {
        viewModelFactory {
            initializer {
               val app =application as MarketPlus
                CoinViewModel(app.repository)
            }
        }
    }
    private var internetObserver: InternetObserver?=null


    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var isOnline by mutableStateOf(isInternetAvailable(applicationContext))
        if(internetObserver==null){
            internetObserver= InternetObserver(
                context = applicationContext,
                onInternetAvailable = { isOnline=true},
                onInternetLost = {isOnline=false}
            )
            internetObserver?.register()
        }

        enableEdgeToEdge()
        setContent {
            MarketplusTheme {
                val navController=rememberNavController()
                NavHost(navController=navController, startDestination ="main_screen"){
                composable("main_screen"){
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        topBar = {
                            CenterAlignedTopAppBar(
                                title = {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(
                                            text = "MarketPlus",
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White
                                        )
                                        Spacer(modifier = Modifier.height(2.dp))
                                        Text(
                                            text =if(isOnline) "🟢Live" else "🔴Offline",
                                            fontSize = 15.sp,
                                            color =if(isOnline) Color(0xFF4CAF50) else Color(0xFFFF5141)
                                        )

                                    }
                                },
                                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                    containerColor = Color(0xFF121212)
                                )
                            )
                        }
                    ) { innerPadding ->
                        marketPlus(
                            viewModel=viewModel,
                            modifier= Modifier.padding(innerPadding),
                            onClick = {coinId->
                                navController.navigate("detail_screen/$coinId")

                            }

                        )
                    }
                }
              composable("detail_screen/{coinId}"){ backStackEntry->
                  val coinId=backStackEntry.arguments?.getString("coinId")?:"bitcoin"
                  val context= LocalContext.current
                  val app=context.applicationContext as MarketPlus
                  val detailViewmodel: CoinDetailViewmodel = viewModel(
                      factory = viewModelFactory {
                          initializer {
                              CoinDetailViewmodel(app.repository,coinId)
                          }
                      }
                  )
                  Scaffold(
                      modifier = Modifier.fillMaxSize(),
                      topBar = {
                          CenterAlignedTopAppBar(
                              title = {
                                  Text(
                                      text = coinId.uppercase(),
                                      fontWeight = FontWeight.Bold
                                  )

                              },
                              navigationIcon = {
                                  IconButton(
                                      onClick = {
                                          navController.popBackStack()
                                      })
                                  {
                                      Icon(
                                          imageVector = Icons.Default.ArrowBack,
                                          contentDescription = "Back Arrow",
                                          tint = Color.White
                                      )
                                  }
                              },
                              colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                  containerColor = Color(0xFF121212)
                              )
                          )
                      }
                  ) {innerPadding->
                      coinDetailScreen(viewmodel=detailViewmodel,modifier= Modifier.padding(innerPadding))
                  }

              }



                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        internetObserver?.unregister()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun marketPlus(
        modifier: Modifier = Modifier,
        viewModel: CoinViewModel,
        onClick:(String)-> Unit
    )
    {


        val coinList=viewModel.coins.collectAsState()
        coinListIteam(coinList,modifier=modifier, onClick ={coinId->onClick(coinId)} )


    }

 }


