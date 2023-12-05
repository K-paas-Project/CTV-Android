package com.kpass.ctv.feature.home

import android.util.Log
import android.widget.HorizontalScrollView
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kpass.ctv.R
import com.kpass.ctv.ui.componet.modifier.ctvClickable
import com.kpass.ctv.ui.theme.Body
import com.kpass.ctv.ui.theme.Caption
import com.kpass.ctv.ui.theme.CtvTheme
import com.kpass.ctv.ui.theme.CtvTypography
import com.kpass.ctv.ui.theme.CustomText
import com.kpass.ctv.utiles.getCategoryColor


@Composable
fun HomeScreen(
    navController: NavController
) {
    val category = listOf("산불", "안전")
    val data = listOf(
        VideoData(
            "fire",
            "대구광역시 팔공산",
            "산불",
            "팔공 IC 네거리",
            R.drawable.video_fire
        ),
        VideoData(
            "video1",
            "대구광역시 팔공산",
            "안전",
            "팔공 IC 네거리",
            R.drawable.video_1
        ),
        VideoData(
            "video2",
            "대구광역시 팔공산",
            "홍수",
            "팔공 IC 네거리",
            R.drawable.video_2
        ),
        VideoData(
            "video3",
            "대구광역시 팔공산",
            "산불",
            "팔공 IC 네거리",
            R.drawable.video_3
        ),
        VideoData(
            "video4",
            "대구광역시 팔공산",
            "안전",
            "팔공 IC 네거리",
            R.drawable.video_4
        ),
        VideoData(
            "video5",
            "대구광역시 팔공산",
            "홍수",
            "팔공 IC 네거리",
            R.drawable.video_5
        ),
    )

    LazyColumn() {
        item {
            Spacer(modifier = Modifier.height(24.dp))
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp)
            ) {
                items(category) {
                    CategoryComponent(
                        modifier = Modifier.ctvClickable(
                            rippleEnable = true
                        ) {
                            Log.d("TAG", "HomeScreen: ")
                        },
                        category = it
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
        itemsIndexed(data) { _, i ->
            ThumbnailVideo(
                modifier = Modifier.ctvClickable(
                    rippleEnable = true
                ) {
                    Log.d("TAG", "HomeScreen: gigigi")
                },
                painter = painterResource(id = i.image),
                contentDescription = "${i.category} 이미지 미리보기",
                location = i.location,
                category = i.category,
                detailLocation = i.detailLocation
            )
        }
    }
}

@Composable
private fun CategoryComponent(
    modifier: Modifier,
    category: String
) {
    Box(
        modifier = Modifier
            .background(Color(0xFFF2F2F2), CtvTheme.shape.normal)
            .then(modifier),
        contentAlignment = Alignment.Center
    ) {
        CustomText(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            text = category,
            textColor = Color(0xF2454545),
            style = CtvTypography.body.copy(
                fontWeight = FontWeight.Medium
            )
        )
    }
}

data class VideoData(
    val url: String,
    val location: String,
    val category: String,
    val detailLocation: String,
    @DrawableRes val image: Int
)

@Composable
private fun ThumbnailVideo(
    modifier: Modifier = Modifier,
    painter: Painter,
    contentDescription: String,
    location: String,
    category: String,
    detailLocation: String
) {
    Column(
        modifier = modifier
    ) {
        Box(modifier = Modifier.height(156.dp)) {
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            Body(text = location)
            Spacer(modifier = Modifier.height(4.dp))
            Row {
                Caption(
                    text = "$category ",
                    textColor = category.getCategoryColor()
                )
                Caption(
                    text = "· $detailLocation",
                    textColor = Color(0xFF76767B)
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

