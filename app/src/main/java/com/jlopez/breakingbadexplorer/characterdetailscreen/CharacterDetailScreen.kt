package com.jlopez.breakingbadexplorer.characterdetailscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.request.ImageRequest
import com.google.accompanist.coil.rememberCoilPainter
import com.jlopez.breakingbadexplorer.characterlistscreen.CharacterListViewModel
import com.jlopez.breakingbadexplorer.data.remote.responses.CharactersItem
import com.jlopez.breakingbadexplorer.ui.theme.DarkGreen
import com.jlopez.breakingbadexplorer.ui.theme.SmokyBlack
import java.util.ArrayList

@Composable
fun CharacterDetailScreen(
    id: Int,
    navController: NavHostController,
    viewModel: CharacterDetailScreenViewModel = hiltViewModel()
) {

    var isLoading by remember{
        viewModel.isLoading
    }
    if(!isLoading){
        val currentCharacter = viewModel.characterList[id - 1]
        Surface(
            color = Color.Black,
            modifier = Modifier
                .fillMaxSize()

        ){
            Column {
                Box(modifier = Modifier
                    .background(Color.Black)
                    .padding(bottom = 16.dp)
                ) {
                    CharacterDetailTopSection(
                        navController = navController,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.2f)
                            .align(Alignment.TopCenter)
                    )
                    CharacterImageSection(character = currentCharacter,
                        modifier = Modifier.align(Alignment.TopCenter))
                }
                Spacer(modifier = Modifier.height(75.dp))
                CharacterDetailSection(character = currentCharacter,
                    modifier = Modifier.fillMaxWidth())
            }
        }
    }
}

@Composable
fun CharacterDetailTopSection (
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.TopStart,
        modifier = modifier
            .background(
                Brush.verticalGradient(
                    listOf(
                        DarkGreen,
                        Color.Black
                    )
                )
            )
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .size(36.dp)
                .offset(16.dp, 16.dp)
                .clickable {
                    navController.popBackStack()
                }
        )
    }
}

@Composable
fun CharacterImageSection(
    character: CharactersItem,
    modifier: Modifier = Modifier,
) {
    val request = ImageRequest.Builder(LocalContext.current)
        .data(character.img)
        .build()
    val imagePainter = rememberCoilPainter(
        request = request)
    Image(
        painter = imagePainter,
        contentDescription = character.name,
        modifier = modifier
            .size(300.dp)
            .padding(top = 80.dp)
            .clip(CircleShape)
    )



}

@Composable
fun CharacterDetailSection(
    character: CharactersItem,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .padding(25.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(SmokyBlack)
    ){
        Column {
            Text(
                text = "Name: " + character.name,
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier.padding(10.dp)
                
            )
            Text(
                text = "Occupation: " + character.occupation,
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier.padding(10.dp)


            )
            Text(
                text = "Status: " + character.status,
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier.padding(10.dp)


            )
            Text(
                text = "Nickname: " + character.nickname,
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier.padding(10.dp)


            )
            Text(
                text = "Season Appearance: " + character.appearance,
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier.padding(10.dp)


            )
        }
    }
}