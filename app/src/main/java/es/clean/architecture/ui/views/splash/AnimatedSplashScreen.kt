package es.clean.architecture.ui.views.splash

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionResult
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import es.clean.architecture.R
import es.clean.architecture.ui.common.MAIN
import kotlinx.coroutines.delay

@Composable
fun AnimatedSplashScreen(navController: NavHostController) {
    var startAnimation by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(2000)
        navController.popBackStack() // to get back and don't show error
        //  navController.navigate(Routes.HomeScreen.route)
        // navController.navigate(Routes.CharacterList.route)
        navController.navigate(MAIN)
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
        speed = 2.0f
    )
    LottieAnimation(
        composition = compositeResult.value,
        progress = progressAnimation,
        modifier = Modifier.fillMaxHeight())
}


