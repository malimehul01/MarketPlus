package com.mddevlabs.marketplus.presentation



import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mddevlabs.marketplus.data.local.CoinEntity

@Composable

fun coinListIteam(coinList: State<List<CoinEntity>>, modifier: Modifier= Modifier,onClick: (String) -> Unit) {

    val coins=coinList.value

    LazyColumn(
       modifier=modifier
    ) {
        items(coins){coin->
            coinListIteamCard(coin, onClick = {onClick(coin.id)})
        }
    }

}

@Composable
fun coinListIteamCard(coin: CoinEntity,onClick:()-> Unit) {
     val isNagative=coin.priceChangePercentage24H<0

     val backGround=if(isNagative)   Color(0xFF3A1E1E) else Color(0xFF1E3A1E)
     val textColor= if(isNagative)Color(0xFFFFB4AB) else  Color(0xFF3FE56C)
      val formateChange= String.format(
          java.util.Locale.US,
          "%.2f%%",
          coin.priceChangePercentage24H
      )
    val formatePrice= String.format(
        java.util.Locale.US,
        "$%.2f",
        coin.currentPrice
    )
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1C1C1E)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .padding(20.dp)
            .clickable{onClick()}

    ){
        Row(
            Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ){

          Text(
              text = "#${coin.marketCapRank}",
          )
            Spacer(modifier = Modifier.width(16.dp))
            AsyncImage(
                model = coin.symbolImg,
                contentDescription = coin.symbolName,
                modifier = Modifier.clip(CircleShape)
                    .size(40.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = coin.symbolName
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(coin.symbol.uppercase())

            }
            Spacer(modifier = Modifier.weight(1f))
            Column {

                Text(formatePrice)
                Spacer(modifier = Modifier.width(4.dp))
                Box(
                    modifier = Modifier
                        .padding(horizontal = 6.dp, vertical =2.dp)
                        .background(backGround, shape = RoundedCornerShape(4.dp))
                ) {
                    Text(text = "${formateChange} ", color = textColor)
                }

            }

        }
    }
}

