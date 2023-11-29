package com.example.mycomposeapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mycomposeapp.R
import com.example.mycomposeapp.ui.theme.MyComposeAppTheme

@Composable
fun ProfileIkan(
    onBackClick : () -> Unit,
    modifier: Modifier = Modifier
){
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(colorResource(R.color.blue_white))
    ){
        item{
            TopBar(modifier, onBackClick)
        }
        item{
            profileContent(modifier)
        }
    }
}

@Preview
@Composable
fun ProfilePreview(){
    MyComposeAppTheme {
        ProfileIkan(onBackClick = { /*TODO*/ })
    }
}

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
){
    Row(
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
                .clickable { onBackClick() }
            )
        Text(
            text = stringResource(R.string.about),
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White
        )
    }
}

@Composable
fun profileContent(
    modifier: Modifier = Modifier
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .padding(top = 20.dp, bottom = 20.dp)
        ) {
            Image(painter = painterResource(
                R.drawable.rehan),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(150.dp)
                    .clip(CircleShape)
            )
            Text(
                text = stringResource(R.string.nama),
                fontSize = 35.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        }
        Card(
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = modifier
                    .padding(start = 16.dp)
                    .fillMaxHeight()
                    .background(Color.White)
            ) {
                DataProfile(
                    title = R.string.domisili,
                    description = R.string.kota_saya
                )
                DataProfile(
                    title = R.string.birthdate,
                    description = R.string.date
                )
                DataProfile(
                    title = R.string.email,
                    description = R.string.my_email
                )
                DataProfile(
                    title = R.string.work,
                    description = R.string.my_work
                )
                DataProfile(
                    title = R.string.kampus,
                    description = R.string.nama_univ
                )
                DataProfile(
                    title = R.string.Prodi,
                    description = R.string.teknik
                )
                DataProfile(
                    title = R.string.linkedIn,
                    description = R.string.link
                )
            }
        }
    }
}

@Composable
fun DataProfile(
    title: Int,
    description: Int,
    modifier: Modifier = Modifier
){
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(title),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = colorResource(R.color.black),
        )
        Text(
            text = stringResource(description),
            color = Color.Black,
            fontSize = 15.sp
        )

    }
}