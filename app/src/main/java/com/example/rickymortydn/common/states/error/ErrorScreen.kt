package com.example.rickymortydn.common.states.error

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ErrorScreen() {
  /*  Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(MaterialTheme.spacing.small)
            .fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.extraMedium)
        ) {
            LoadGif(
                img = R.drawable.error_awkward_gif,
                modifier = Modifier
                    .size(MaterialTheme.dimens.custom200)
                    .clip(MaterialTheme.shapes.large)
            )

            Spacer(Modifier.height(MaterialTheme.spacing.extraMedium))

            Text(
                text = stringResource(R.string.error),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.error,
            )

            Spacer(Modifier.height(MaterialTheme.spacing.semiSmall))

            Text(
                text = stringResource(R.string.try_reload),
                style = MaterialTheme.typography.bodyMedium,
            )

        }
    }*/
}

@Preview("Light Theme", showBackground = true)
@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ErrorScreenPreview() {
    ErrorScreen()
}