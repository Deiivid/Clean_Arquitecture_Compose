package es.clean.architecture.ui.views.characters.screens.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.airbnb.lottie.compose.*
import es.clean.architecture.R
import es.clean.architecture.domain.characters.models.character.RickyMortyCharacterModel
import es.clean.architecture.domain.characters.models.character.createCharacterResult
import es.clean.architecture.ui.common.navigation.routes.Routes
import es.clean.architecture.ui.views.characters.viewmodel.CharactersViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersListScreen(
    navController: NavHostController,
    charactersViewModel: CharactersViewModel = hiltViewModel(),
) {
    val characters = charactersViewModel.allCharacters.collectAsLazyPagingItems()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    var isSearching by remember {
        mutableStateOf(false)
    }
    var searchString by remember {
        mutableStateOf("")
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier
                    .fillMaxWidth(),
                title = {
                    if (!isSearching) {
                        Text(
                            text = stringResource(id = R.string.app_name),
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    } else {
                        /*TextField(
                            modifier = Modifier
                                .padding(end = 12.dp)
                                .fillMaxWidth(),
                            value = searchString,
                            onValueChange = { newSearchString ->
                                searchString = newSearchString
                            },
                            label = { Text("Cadena de Búsqueda") },
                            maxLines = 1
                        )*/
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
               /* actions = {
                    IconButton(onClick = { isSearching = !isSearching }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }*/
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(
                count = characters.itemCount,
                key = characters.itemKey { character -> character.id }
            ) { characterIndex ->
                characters[characterIndex]?.let { item ->
                    CharacterItem(
                        character = item,
                    ) { currentCharacter ->

                        navController.currentBackStackEntry?.savedStateHandle?.set(
                            "character",
                            value = currentCharacter
                        )
                        navController.navigate(Routes.CharacterDetailScreen.route)

                        /* scope.launch {
                             withContext(Dispatchers.Main){
                                 Toast.makeText(context, "Personaje: ${currentCharacter.name}", Toast.LENGTH_LONG).show()
                             }
                         }
                     }*/
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalCoilApi::class)
@Composable
fun CharacterItem(
    character: RickyMortyCharacterModel.RickyMortyCharacter,
    onItemClick: (rickyMortyCharacter: RickyMortyCharacterModel.RickyMortyCharacter) -> Unit
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Color(0xFFDAE1E7),
        modifier = Modifier
            .clickable {
                onItemClick(character)
            }
            .height(165.dp)
            .padding(10.dp),
        shadowElevation = 10.dp
    ) {
        Row(
            modifier = Modifier.padding(bottom = 14.dp, top = 8.dp, start = 8.dp, end = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(2f),
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier.wrapContentSize(),
                    contentAlignment = Alignment.Center,
                    content = {
                        Text(
                            text = character.status,
                            fontSize = 12.sp,
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier
                                .padding(vertical = 4.dp, horizontal = 8.dp)
                                .clip(RoundedCornerShape(14.dp))
                                .background(Color(0xFFD1D5E1))
                        )
                    }
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = character.name,
                    fontSize = 20.sp,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(text = character.gender)

                Spacer(modifier = Modifier.height(2.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = character.characterLocation.name,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                }

                Spacer(modifier = Modifier.height(4.dp))
            }
            Spacer(modifier = Modifier.width(12.dp))
            Box(
                modifier = Modifier
                    .size(width = 135.dp, height = 110.dp)
                    .padding(top = 10.dp),
                contentAlignment = Alignment.Center,
                content = {
                    Image(
                        painter = rememberImagePainter(data = character.image),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color.White)
                    )
                }
            )
        }
    }
}


@Composable
fun LottieProgressBar() {
    val compositeResult: LottieCompositionResult = rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.loadinglottie)
    )
    val progressAnimation by animateLottieCompositionAsState(
        composition = compositeResult.value,
        isPlaying = true,
        iterations = LottieConstants.IterateForever,
        speed = 1.0f
    )
    LottieAnimation(
        composition = compositeResult.value,
        progress = progressAnimation,
        modifier = Modifier.fillMaxSize()
    )

}

@Composable
fun LottieErrorState() {
    val compositeResult: LottieCompositionResult = rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.cryricky)
    )
    val progressAnimation by animateLottieCompositionAsState(
        composition = compositeResult.value,
        isPlaying = true,
        iterations = LottieConstants.IterateForever,
        speed = 1.0f
    )
    LottieAnimation(
        composition = compositeResult.value,
        progress = progressAnimation,
        modifier = Modifier.fillMaxSize()
    )

}

@Preview(widthDp = 320, heightDp = 480)
@Composable
fun CharacterListScreenPreview() {
    val character = listOf(createCharacterResult())
    val onItemClick: (RickyMortyCharacterModel.RickyMortyCharacter) -> Unit = { }
//    CharacterItem(items = character, onItemClick = onItemClick)
}
