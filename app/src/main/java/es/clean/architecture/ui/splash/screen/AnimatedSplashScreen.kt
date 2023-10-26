package es.clean.architecture.ui.splash.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.*
import es.clean.architecture.R

import es.clean.architecture.ui.common.navigation.Routes
import kotlinx.coroutines.delay

@Composable
fun AnimatedSplashScreen(navController: NavHostController) {
    var startAnimation by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(3000)
        navController.popBackStack() // to get back and dont show error
        navController.navigate(Routes.CharacterList.route)
    }
    LottieSplashScreen()
}

@Composable
fun LottieSplashScreen() {
    val compositeResult: LottieCompositionResult = rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.splashscreen)
    )
    val progressAnimation by animateLottieCompositionAsState(
        composition = compositeResult.value,
        isPlaying = true,
        iterations = LottieConstants.IterateForever,
        speed = 1.0f
    )
    LottieAnimation(composition = compositeResult.value, progress = progressAnimation, modifier = Modifier.fillMaxSize())
}