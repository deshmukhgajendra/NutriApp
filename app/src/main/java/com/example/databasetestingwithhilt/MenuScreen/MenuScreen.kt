package com.example.databasetestingwithhilt.MenuScreen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.databasetestingwithhilt.R
import com.example.databasetestingwithhilt.ui.theme.OutFitFontFamily
import com.example.databasetestingwithhilt.ui.theme.White
import com.example.databasetestingwithhilt.ui.theme.gray
import com.example.databasetestingwithhilt.ui.theme.purple
import com.example.databasetestingwithhilt.ui.theme.sea


@Composable
fun MenuScreen() {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
    ) {

        Card(
            modifier = Modifier
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp), // Rounded corners
            colors = CardDefaults.cardColors(containerColor = gray) // Set card background color
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = "Settings",
                    fontSize = 20.sp,
                    style = TextStyle(
                        fontFamily = OutFitFontFamily,
                        fontWeight = FontWeight.ExtraBold
                    ),
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                )
                CustomButtonRow("My Profile", R.drawable.baseline_tag_faces_24, purple,{})
                CustomButtonRow("Sleep settings", R.drawable.sleep, purple,{})
                CustomButtonRow("Permissions assistant", R.drawable.baseline_security_24, purple,{})
                CustomButtonRow("General settings", R.drawable.baseline_settings_24, purple,{})
                CustomButtonRow("Language", R.drawable.baseline_language_24, purple,{})
            }
        }

        Card(
            modifier = Modifier
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp), // Rounded corners
            colors = CardDefaults.cardColors(containerColor = gray) // Set card background color
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                CustomButtonRow("Remove ads", R.drawable.baseline_do_not_disturb_24, sea,{})
                CustomButtonRow("FAQ", R.drawable.round_question_mark_24, sea,{})
                CustomButtonRow("Feedback", R.drawable.baseline_feedback_24, sea,{})
                CustomButtonRow("Share with friends", R.drawable.baseline_share_24, sea,{})
                CustomButtonRow("Rate us", R.drawable.baseline_star_24, sea,{})
                CustomButtonRow("Privacy Policy", R.drawable.baseline_assignment_24, sea,{})
            }
        }
    }
}


@Composable
fun CustomButtonRow(
    buttonTitle: String,
    @DrawableRes iconResId: Int,
    color: Color,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() }
            ,verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(35.dp)
                    .clip(RoundedCornerShape(20))
                    .background(color),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = iconResId),
                    contentDescription = "Icon for $buttonTitle",
                    tint = White,
                    modifier = Modifier.size(25.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp)) // Spacing between icon and text
            Text(
                text = buttonTitle,
                style = TextStyle(
                    fontFamily = OutFitFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                ),
                modifier = Modifier.weight(1f)
            )
        }
        Divider(
            color = MaterialTheme.colorScheme.surfaceVariant,
            thickness = 1.dp,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}
