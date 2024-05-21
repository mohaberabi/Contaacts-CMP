package core.presentation.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ContactTextField(
    modifier: Modifier = Modifier,
    value: String,
    onChanged: (String) -> Unit,
    hint: String,
    label: String,
    error: String? = null,
) {


    Column {

        OutlinedTextField(
            label = {

                Text(label, color = Color.Black)
            },
            modifier = modifier.fillMaxWidth(),
            value = value,
            onValueChange = onChanged,
            shape = RoundedCornerShape(8.dp),
            placeholder = {
                Text(
                    hint,
                    color = Color.LightGray,
                    fontSize = 12.sp
                )
            }
        )
        if (error != null) {
            Text(
                error, color = Color.Red,
                fontSize = 8.sp,
            )
        }
    }

}