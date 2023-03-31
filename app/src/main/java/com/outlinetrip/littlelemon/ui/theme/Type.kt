package com.outlinetrip.littlelemon.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.outlinetrip.littlelemon.R

/**
 * Parragraph Sentence Case Size: 65% of headline
 */
private val karla_regular = FontFamily(
    Font(R.font.karla_regular)
)

/***
 * Headlines Sentence case Leading: same point size as headline
* */

private val markazitext_regular = FontFamily(
    Font(R.font.MarkaziText_Regular)
)


// Set of Material typography styles to start with
val LittleLemonTypography = Typography(
    body1 = TextStyle(
        fontFamily = markazitext_regular,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    button = TextStyle(
        fontFamily = markazitext_regular,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
        color = llWhite
    ),
    caption = TextStyle(
        fontFamily = karla_regular,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
)