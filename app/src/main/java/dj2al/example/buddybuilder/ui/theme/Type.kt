package dj2al.example.buddybuilder.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import dj2al.example.buddybuilder.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.oswald_variablefont_wght)),
        fontWeight = FontWeight.W700,
        fontSize = 25.sp,
        lineHeight = 37.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.oswald_variablefont_wght)),
        fontWeight = FontWeight.W700,
        fontSize = 25.sp,
        lineHeight = 37.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.oswald_variablefont_wght)),
        fontWeight = FontWeight.W700,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)