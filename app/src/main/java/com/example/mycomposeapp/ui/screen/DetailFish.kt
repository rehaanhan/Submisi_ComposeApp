package com.example.mycomposeapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mycomposeapp.viewmodel.FishViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.mycomposeapp.R
import com.example.mycomposeapp.viewmodel.ViewModelFactory
import com.example.mycomposeapp.data.FishRepository
import com.example.mycomposeapp.model.Ikan
import com.example.mycomposeapp.ui.theme.MyComposeAppTheme

@Composable
fun DetailFish(
    fishId: String,
    modifier: Modifier = Modifier,
    viewModel: FishViewModel = viewModel(
        factory = ViewModelFactory(FishRepository())
    ),
    navigateBack: () -> Unit
){
    val fish = viewModel.searchById(fishId)
    
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.blue_white))
    ){
        item{
            TopAppBar (modifier, navigateBack)
        }
        item {
            DetailFishApp(ikan = fish)
        }
    }
}

@Composable
fun TopAppBar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
){
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(colorResource(R.color.biru))
            .fillMaxWidth()
    ){
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .padding(16.dp)
                .clickable { onBackClick }
        )
        Text(
            text = stringResource(R.string.detail_fish),
            fontSize = 25.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White
            )
    }
}

@Composable
fun DetailFishApp(
    modifier: Modifier = Modifier,
    ikan: Ikan
){
    Card(
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column (
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            AsyncImage(
                model = ikan.photoUrl,
                contentDescription = null,
                modifier = modifier
                    .width(250.dp)
                    .height(250.dp)
                    .size(120.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = ikan.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, top = 16.dp)
            )
            Text(
                text = ikan.description,
                fontSize = 15.sp,
                textAlign = TextAlign.Justify,
                modifier = modifier
                    .padding(10.dp)
                )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailFishAppPreview(){
    MyComposeAppTheme {
        DetailFish(
            fishId = "1",
            navigateBack = {}
        )
    }
}