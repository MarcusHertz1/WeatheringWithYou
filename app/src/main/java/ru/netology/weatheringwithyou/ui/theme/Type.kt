package ru.netology.weatheringwithyou.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp,
        lineHeight = 36.sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 28.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)
    /*
<TextView
android:id="@+id/some_id"
android:layout_width="211dp"
android:layout_height="44dp"
android:layout_alignParentLeft="true"
android:layout_marginLeft="16dp"
android:layout_alignParentTop="true"
android:layout_marginTop="63dp"
android:text="@string/some_id"
android:textAppearance="@style/some_id"
android:lineSpacingExtra="2sp"
android:translationY="-0.91sp"
android:gravity="top"
/>
<!--
Font family: Roboto
Line height: 44sp
(identical to box height)
translationY compensates for the line height adjustment of text
-->

<!-- styles.xml -->
<style name="some_id">
<item name="android:textSize">36sp</item>
<item name="android:textColor">#B3FFFFFF</item>
</style>

<!-- strings.xml -->
<string name="some_id">
\u00d0\u0092\u00d1\u0081\u00d0\u00b5\u00d0\u00b2\u00d0\u00be\u00d0\u00bb\u00d0\u00be\u00d0\u00b6\u00d1\u0081\u00d0\u00ba
</string>

*/