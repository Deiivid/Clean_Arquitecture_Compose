package es.clean.architecture.ui.common.states.loading

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LoadingScreen() {
    /* val loading by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottie_loading_bar))
     Box(
         contentAlignment = Alignment.Center,
         modifier = Modifier.fillMaxSize()
     ) {
         LottieAnimation(
             composition = loading,
             isPlaying = true,
             restartOnPlay = true,
             iterations = LottieConstants.IterateForever,
             modifier = Modifier
                 .height(MaterialTheme.dimens.custom250)
         )
     }*/
}

@Preview("Light Theme", showBackground = true)
@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun LoadingScreenPreview() {
    LoadingScreen()
}