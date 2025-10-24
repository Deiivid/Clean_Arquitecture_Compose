package es.clean.architecture.ui.views.characters.screens.search.customization

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import es.clean.architecture.ui.common.Dimens.ExtraLarge
import es.clean.architecture.ui.common.Dimens.Large
import es.clean.architecture.ui.common.Dimens.Tiny
import es.clean.architecture.ui.views.characters.common.CutCornersCustom


@Composable
fun SearchNameField(
    value: String,
    onValueChange: (String) -> Unit,
    onSearch: () -> Unit,
    placeholder: @Composable () -> Unit
) {
    val backgroundColor =
        MaterialTheme.colorScheme.primary.copy(alpha = 0.9f)
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = androidx.compose.ui.text.input.ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                if (value.isNotEmpty()) {
                    onValueChange(value)
                    onSearch()
                }
            }
        ),
        decorationBox = { innerTextField ->
            Surface(
                shape = CutCornersCustom(ExtraLarge),
                color = backgroundColor,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = Tiny,
                        color = LocalContentColor.current,
                        shape = CutCornersCustom(ExtraLarge)
                    )
                    .padding(Large),
                shadowElevation = Large
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(ExtraLarge)
                ) {
                    if (value.isEmpty()) {
                        placeholder()
                    }
                    innerTextField()
                }
            }
        }
    )
}
