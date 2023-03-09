package com.dbtechprojects.photonotes.screens.info

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.*
import androidx.navigation.NavHostController
import com.dbtechprojects.photonotes.R

@Composable
fun MyInfo_fare(navController: NavHostController) {
    var text by remember { mutableStateOf("") }
    Column {
        Info_fare(navController, contentDescription = "내용입니다.",)
    }
}

    @Composable
    fun Info_fare(
        painter: NavHostController,
        contentDescription: String?,
        modifier: Modifier = Modifier,
        alignment: Alignment = Alignment.Center,
        contentScale: ContentScale = ContentScale.Fit,
        alpha: Float = DefaultAlpha,
        colorFilter: ColorFilter? = null,
    ) {
        val title1 = "vip : 5,900원"
        val title2 = "vvip : 9,900원"

            Column(modifier = Modifier.padding(60.dp)) {

                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = "이용권구매", fontSize = 30.sp)
                }

                Divider(color = Color.Gray, thickness = 2.dp)

                    Text(text = "사용중인 이용권", fontSize = 18.sp)
                    Text(text = "사용중인 이용권이 없습니다.", fontSize = 20.sp)
                    Text(text = "이용권을 구매하고 다양한 해택을 누리세요!", fontSize = 14.sp, color = Color.LightGray)

                Divider(color = Color.Gray, thickness = 2.dp)

                Box(modifier = modifier.height(200.dp)) {
                    Image(
                        painter = painterResource(R.drawable.vip),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.aspectRatio(14f/8f)
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        Color.Black,
                                    ),
                                    startY = 300f
                                )
                            )
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(12.dp),
                        contentAlignment = Alignment.BottomStart
                    ) {
                        Text(title1, style = TextStyle(color = Color.White, fontSize = 16.sp))
                    }
                }
                Box(modifier = modifier.height(200.dp)) {
                    Image(
                        painter = painterResource(R.drawable.vvip),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.aspectRatio(14f/8f)
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        Color.Black,
                                    ),
                                    startY = 300f
                                )
                            )
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(12.dp),
                        contentAlignment = Alignment.BottomStart
                    ) {
                        Text(title2, style = TextStyle(color = Color.White, fontSize = 16.sp))
                    }
                }

                Divider(color = Color.Gray, thickness = 2.dp)

            }
        }
