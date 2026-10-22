package com.mddevlabs.marketplus.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TrendingDown
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage


@Composable
fun coinDetailScreen(viewmodel: CoinDetailViewmodel, modifier: Modifier) {
    val coinData =viewmodel.coin.collectAsState()
    val coin=coinData.value
    if(coin!=null){
    val isNagative= coin.priceChangePercentage24H < 0
    val backGround=if(isNagative)   Color(0xFF3A1E1E) else Color(0xFF1E3A1E)
    val textColor= if(isNagative)Color(0xFFFFB4AB) else  Color(0xFF3FE56C)
        val  icon=if(isNagative) Icons.Default.TrendingDown else Icons.Default.TrendingUp
        val formateChange= String.format(
            java.util.Locale.US,
            "%.2f%%",
            coin.priceChangePercentage24H
        )
        fun formatCryptoNumber(value: Double): String {
            return when {
                value >= 1_000_000_000_000 -> String.format(java.util.Locale.US, "$%.2fT", value / 1_000_000_000_000) // Trillion
                value >= 1_000_000_000 -> String.format(java.util.Locale.US, "$%.2fB", value / 1_000_000_000)       // Billion
                value >= 1_000_000 -> String.format(java.util.Locale.US, "$%.2fM", value / 1_000_000)             // Million
                else -> String.format(java.util.Locale.US, "$%.2f", value)                                       // Normal
            }
        }
    Column(
        modifier = modifier.fillMaxSize()
            .padding(20.dp),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        AsyncImage(
            model = coin.symbolImg,
            contentDescription = "${coin.symbolName} image",
            modifier = Modifier.clip(CircleShape)
                .size(64.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "${formatCryptoNumber(coin.currentPrice)} USD",
            fontWeight = FontWeight.Bold,
            fontSize = 50.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical =3.dp)
                .background(backGround, shape = RoundedCornerShape(4.dp))
        ) {
            Row {

                Icon(
                    imageVector = icon,
                    contentDescription = "icon",
                    tint = textColor,

                )
                Spacer(modifier=Modifier.width(6.dp))
                Text(

                    text = " ${formateChange} Today ",
                    color = textColor
                )

            }
        }
        Spacer(modifier= Modifier.height(16.dp))

        Row(modifier.fillMaxWidth(),horizontalArrangement = Arrangement.spacedBy(8.dp)){

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF1C1C1E)
                ),
                modifier = Modifier.weight(1f)
                    .height(120.dp)

            ) {
                Column(
                    modifier= Modifier.fillMaxWidth().padding(10.dp),
                  verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Market Cap",
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = formatCryptoNumber(coin.marketCap),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }


            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF1C1C1E)
                ),
                modifier = Modifier.weight(1f)
                    .height(120.dp)

            ) {
                Column(
                    modifier= Modifier.fillMaxWidth().padding(10.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Total Volume",
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text =formatCryptoNumber(coin.totalVolume),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
        Row(modifier.fillMaxWidth(),horizontalArrangement = Arrangement.spacedBy(8.dp)){

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF1C1C1E)
                ),
                modifier = Modifier.weight(1f)
                    .height(120.dp)
            ) {
                Column(
                    modifier= Modifier.fillMaxWidth().padding(10.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "24h High/Low",
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text =formatCryptoNumber(coin.high24),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF3FE56C)
                    )
                    Text(
                        text =formatCryptoNumber(coin.low24),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFFFB4AB)
                    )
                }
            }


            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF1C1C1E)
                ),
                modifier = Modifier.weight(1f)
                    .height(120.dp)

            ) {
                Column(
                    modifier= Modifier.fillMaxWidth().padding(10.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "All-Time High ",
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text =formatCryptoNumber(coin.ath),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

    }

    }

    else{
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Loading coin details...", color = Color.Gray, fontSize = 16.sp)
        }
    }
}