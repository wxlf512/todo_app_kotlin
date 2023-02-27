package dev.wxlf.todoapp.presentation.elements

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TransparentBackgroundTextField(
    value: String,
    placeholder: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle = LocalTextStyle.current,
    singleLine: Boolean = false,
    textColor: Color = MaterialTheme.colorScheme.onSurface,
    placeholderTextStyle: TextStyle = TextStyle(color = Color.LightGray, fontWeight = FontWeight.Normal, fontSize = 18.sp),
    enabled: Boolean = true,
    cursorColor: Color = MaterialTheme.colorScheme.primary
) {
    val mergedTextStyle = textStyle.merge(TextStyle(color = textStyle.color.takeOrElse { textColor }))

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = mergedTextStyle,
        singleLine = singleLine,
        enabled = enabled,
        cursorBrush = SolidColor(cursorColor),
        decorationBox = { innerTextField ->
            Box(
                modifier = modifier
                    .padding(horizontal = 16.dp, vertical = 12.dp), // inner padding
            ) {
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = placeholderTextStyle
                    )
                }
                innerTextField()
            }
        }
    )

}