package com.example.mycomposeapp.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.mycomposeapp.R
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mycomposeapp.viewmodel.ViewModelFactory
import com.example.mycomposeapp.data.FishRepository
import com.example.mycomposeapp.model.IkanData
import com.example.mycomposeapp.viewmodel.FishViewModel
import com.example.mycomposeapp.ui.theme.MyComposeAppTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeIkan(
    modifier: Modifier = Modifier,
    viewModel: FishViewModel = viewModel(
        factory = ViewModelFactory(FishRepository())
    ),
    navToDetail: (String) -> Unit,
){
    val groupedSeries by viewModel.groupedFish.collectAsState()
    val query by viewModel.query

    Box(modifier = modifier) {
        val scope = rememberCoroutineScope()
        val listState = rememberLazyListState()
        val showButton: Boolean by remember {
            derivedStateOf { listState.firstVisibleItemIndex > 0 }
        }
        LazyColumn{

            item {
                 Searchbar(
                     query = query,
                     onQueryChange = viewModel::search,
                     modifier = Modifier.background(MaterialTheme.colorScheme.primary)
                 )
            }
            groupedSeries.forEach{ (initial, fish) ->
                stickyHeader {
                    CharacterHeader(initial)
                }
                items(fish, key = { it.id }) { ikan ->
                    FishListItem(
                        id = ikan.id,
                        name = ikan.name,
                        photoUrl = ikan.photoUrl,
                        modifier = Modifier.fillMaxWidth(),
                        navigateToDetail = navToDetail
                    )
                }
            }

        }
    }
}

@Composable
fun CharacterHeader(
    char: Char,
    modifier: Modifier = Modifier
) {
    Surface(
        color = colorResource(R.color.biru),
        modifier = modifier
    ) {
        Text(
            text = char.toString(),
            fontWeight = FontWeight.Black,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Searchbar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier =Modifier
){
    SearchBar(
        query = query,
        onQueryChange = onQueryChange,
        onSearch = {},
        active = false,
        onActiveChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        colors = SearchBarDefaults.colors(
            containerColor = LightGray
        ),
        placeholder = {
            Text(text = stringResource(id = R.string.search_Fish))
        },
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .heightIn(min = 48.dp)
            .clip(RoundedCornerShape(16.dp))

    ) {
        
    }
}

@Composable
fun FishListItem(
    id: String,
    name: String,
    photoUrl: String,
    modifier: Modifier = Modifier,
    navigateToDetail: (String) -> Unit,

) {
    Card(
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .padding(8.dp)
                .height(100.dp)
                .clickable { navigateToDetail(id) }
        ) {
            AsyncImage(
                model = photoUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(8.dp)
                    .size(85.dp)
                    .clip(CircleShape)
            )
            Column {

                Text(
                    text = name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp)
                )
                Text(
                    text = stringResource(R.string.view_Detail),
                    fontSize = 14.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp)
                )
            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun HeroListItemPreview() {
    MyComposeAppTheme {
        HomeIkan(
            navToDetail = {},
        )
    }
}