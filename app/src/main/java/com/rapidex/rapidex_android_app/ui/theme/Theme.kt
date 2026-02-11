package com.example.compose
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.ui.theme.RapidexTypography
import com.rapidex.rapidex_android_app.ui.theme.backgroundDark
import com.rapidex.rapidex_android_app.ui.theme.backgroundDarkHighContrast
import com.rapidex.rapidex_android_app.ui.theme.backgroundDarkMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.backgroundLight
import com.rapidex.rapidex_android_app.ui.theme.backgroundLightHighContrast
import com.rapidex.rapidex_android_app.ui.theme.backgroundLightMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.errorContainerDark
import com.rapidex.rapidex_android_app.ui.theme.errorContainerDarkHighContrast
import com.rapidex.rapidex_android_app.ui.theme.errorContainerDarkMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.errorContainerLight
import com.rapidex.rapidex_android_app.ui.theme.errorContainerLightHighContrast
import com.rapidex.rapidex_android_app.ui.theme.errorContainerLightMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.errorDark
import com.rapidex.rapidex_android_app.ui.theme.errorDarkHighContrast
import com.rapidex.rapidex_android_app.ui.theme.errorDarkMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.errorLight
import com.rapidex.rapidex_android_app.ui.theme.errorLightHighContrast
import com.rapidex.rapidex_android_app.ui.theme.errorLightMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.inverseOnSurfaceDark
import com.rapidex.rapidex_android_app.ui.theme.inverseOnSurfaceDarkHighContrast
import com.rapidex.rapidex_android_app.ui.theme.inverseOnSurfaceDarkMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.inverseOnSurfaceLight
import com.rapidex.rapidex_android_app.ui.theme.inverseOnSurfaceLightHighContrast
import com.rapidex.rapidex_android_app.ui.theme.inverseOnSurfaceLightMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.inversePrimaryDark
import com.rapidex.rapidex_android_app.ui.theme.inversePrimaryDarkHighContrast
import com.rapidex.rapidex_android_app.ui.theme.inversePrimaryDarkMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.inversePrimaryLight
import com.rapidex.rapidex_android_app.ui.theme.inversePrimaryLightHighContrast
import com.rapidex.rapidex_android_app.ui.theme.inversePrimaryLightMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.inverseSurfaceDark
import com.rapidex.rapidex_android_app.ui.theme.inverseSurfaceDarkHighContrast
import com.rapidex.rapidex_android_app.ui.theme.inverseSurfaceDarkMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.inverseSurfaceLight
import com.rapidex.rapidex_android_app.ui.theme.inverseSurfaceLightHighContrast
import com.rapidex.rapidex_android_app.ui.theme.inverseSurfaceLightMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.onBackgroundDark
import com.rapidex.rapidex_android_app.ui.theme.onBackgroundDarkHighContrast
import com.rapidex.rapidex_android_app.ui.theme.onBackgroundDarkMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.onBackgroundLight
import com.rapidex.rapidex_android_app.ui.theme.onBackgroundLightHighContrast
import com.rapidex.rapidex_android_app.ui.theme.onBackgroundLightMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.onErrorContainerDark
import com.rapidex.rapidex_android_app.ui.theme.onErrorContainerDarkHighContrast
import com.rapidex.rapidex_android_app.ui.theme.onErrorContainerDarkMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.onErrorContainerLight
import com.rapidex.rapidex_android_app.ui.theme.onErrorContainerLightHighContrast
import com.rapidex.rapidex_android_app.ui.theme.onErrorContainerLightMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.onErrorDark
import com.rapidex.rapidex_android_app.ui.theme.onErrorDarkHighContrast
import com.rapidex.rapidex_android_app.ui.theme.onErrorDarkMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.onErrorLight
import com.rapidex.rapidex_android_app.ui.theme.onErrorLightHighContrast
import com.rapidex.rapidex_android_app.ui.theme.onErrorLightMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.onPrimaryContainerDark
import com.rapidex.rapidex_android_app.ui.theme.onPrimaryContainerDarkHighContrast
import com.rapidex.rapidex_android_app.ui.theme.onPrimaryContainerDarkMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.onPrimaryContainerLight
import com.rapidex.rapidex_android_app.ui.theme.onPrimaryContainerLightHighContrast
import com.rapidex.rapidex_android_app.ui.theme.onPrimaryContainerLightMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.onPrimaryDark
import com.rapidex.rapidex_android_app.ui.theme.onPrimaryDarkHighContrast
import com.rapidex.rapidex_android_app.ui.theme.onPrimaryDarkMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.onPrimaryLight
import com.rapidex.rapidex_android_app.ui.theme.onPrimaryLightHighContrast
import com.rapidex.rapidex_android_app.ui.theme.onPrimaryLightMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.onSecondaryContainerDark
import com.rapidex.rapidex_android_app.ui.theme.onSecondaryContainerDarkHighContrast
import com.rapidex.rapidex_android_app.ui.theme.onSecondaryContainerDarkMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.onSecondaryContainerLight
import com.rapidex.rapidex_android_app.ui.theme.onSecondaryContainerLightHighContrast
import com.rapidex.rapidex_android_app.ui.theme.onSecondaryContainerLightMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.onSecondaryDark
import com.rapidex.rapidex_android_app.ui.theme.onSecondaryDarkHighContrast
import com.rapidex.rapidex_android_app.ui.theme.onSecondaryDarkMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.onSecondaryLight
import com.rapidex.rapidex_android_app.ui.theme.onSecondaryLightHighContrast
import com.rapidex.rapidex_android_app.ui.theme.onSecondaryLightMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.onSurfaceDark
import com.rapidex.rapidex_android_app.ui.theme.onSurfaceDarkHighContrast
import com.rapidex.rapidex_android_app.ui.theme.onSurfaceDarkMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.onSurfaceLight
import com.rapidex.rapidex_android_app.ui.theme.onSurfaceLightHighContrast
import com.rapidex.rapidex_android_app.ui.theme.onSurfaceLightMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.onSurfaceVariantDark
import com.rapidex.rapidex_android_app.ui.theme.onSurfaceVariantDarkHighContrast
import com.rapidex.rapidex_android_app.ui.theme.onSurfaceVariantDarkMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.onSurfaceVariantLight
import com.rapidex.rapidex_android_app.ui.theme.onSurfaceVariantLightHighContrast
import com.rapidex.rapidex_android_app.ui.theme.onSurfaceVariantLightMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.onTertiaryContainerDark
import com.rapidex.rapidex_android_app.ui.theme.onTertiaryContainerDarkHighContrast
import com.rapidex.rapidex_android_app.ui.theme.onTertiaryContainerDarkMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.onTertiaryContainerLight
import com.rapidex.rapidex_android_app.ui.theme.onTertiaryContainerLightHighContrast
import com.rapidex.rapidex_android_app.ui.theme.onTertiaryContainerLightMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.onTertiaryDark
import com.rapidex.rapidex_android_app.ui.theme.onTertiaryDarkHighContrast
import com.rapidex.rapidex_android_app.ui.theme.onTertiaryDarkMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.onTertiaryLight
import com.rapidex.rapidex_android_app.ui.theme.onTertiaryLightHighContrast
import com.rapidex.rapidex_android_app.ui.theme.onTertiaryLightMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.outlineDark
import com.rapidex.rapidex_android_app.ui.theme.outlineDarkHighContrast
import com.rapidex.rapidex_android_app.ui.theme.outlineDarkMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.outlineLight
import com.rapidex.rapidex_android_app.ui.theme.outlineLightHighContrast
import com.rapidex.rapidex_android_app.ui.theme.outlineLightMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.outlineVariantDark
import com.rapidex.rapidex_android_app.ui.theme.outlineVariantDarkHighContrast
import com.rapidex.rapidex_android_app.ui.theme.outlineVariantDarkMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.outlineVariantLight
import com.rapidex.rapidex_android_app.ui.theme.outlineVariantLightHighContrast
import com.rapidex.rapidex_android_app.ui.theme.outlineVariantLightMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.primaryContainerDark
import com.rapidex.rapidex_android_app.ui.theme.primaryContainerDarkHighContrast
import com.rapidex.rapidex_android_app.ui.theme.primaryContainerDarkMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.primaryContainerLight
import com.rapidex.rapidex_android_app.ui.theme.primaryContainerLightHighContrast
import com.rapidex.rapidex_android_app.ui.theme.primaryContainerLightMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.primaryDark
import com.rapidex.rapidex_android_app.ui.theme.primaryDarkHighContrast
import com.rapidex.rapidex_android_app.ui.theme.primaryDarkMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.primaryLight
import com.rapidex.rapidex_android_app.ui.theme.primaryLightHighContrast
import com.rapidex.rapidex_android_app.ui.theme.primaryLightMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.scrimDark
import com.rapidex.rapidex_android_app.ui.theme.scrimDarkHighContrast
import com.rapidex.rapidex_android_app.ui.theme.scrimDarkMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.scrimLight
import com.rapidex.rapidex_android_app.ui.theme.scrimLightHighContrast
import com.rapidex.rapidex_android_app.ui.theme.scrimLightMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.secondaryContainerDark
import com.rapidex.rapidex_android_app.ui.theme.secondaryContainerDarkHighContrast
import com.rapidex.rapidex_android_app.ui.theme.secondaryContainerDarkMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.secondaryContainerLight
import com.rapidex.rapidex_android_app.ui.theme.secondaryContainerLightHighContrast
import com.rapidex.rapidex_android_app.ui.theme.secondaryContainerLightMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.secondaryDark
import com.rapidex.rapidex_android_app.ui.theme.secondaryDarkHighContrast
import com.rapidex.rapidex_android_app.ui.theme.secondaryDarkMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.secondaryLight
import com.rapidex.rapidex_android_app.ui.theme.secondaryLightHighContrast
import com.rapidex.rapidex_android_app.ui.theme.secondaryLightMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.surfaceBrightDark
import com.rapidex.rapidex_android_app.ui.theme.surfaceBrightDarkHighContrast
import com.rapidex.rapidex_android_app.ui.theme.surfaceBrightDarkMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.surfaceBrightLight
import com.rapidex.rapidex_android_app.ui.theme.surfaceBrightLightHighContrast
import com.rapidex.rapidex_android_app.ui.theme.surfaceBrightLightMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.surfaceContainerDark
import com.rapidex.rapidex_android_app.ui.theme.surfaceContainerDarkHighContrast
import com.rapidex.rapidex_android_app.ui.theme.surfaceContainerDarkMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.surfaceContainerHighDark
import com.rapidex.rapidex_android_app.ui.theme.surfaceContainerHighDarkHighContrast
import com.rapidex.rapidex_android_app.ui.theme.surfaceContainerHighDarkMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.surfaceContainerHighLight
import com.rapidex.rapidex_android_app.ui.theme.surfaceContainerHighLightHighContrast
import com.rapidex.rapidex_android_app.ui.theme.surfaceContainerHighLightMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.surfaceContainerHighestDark
import com.rapidex.rapidex_android_app.ui.theme.surfaceContainerHighestDarkHighContrast
import com.rapidex.rapidex_android_app.ui.theme.surfaceContainerHighestDarkMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.surfaceContainerHighestLight
import com.rapidex.rapidex_android_app.ui.theme.surfaceContainerHighestLightHighContrast
import com.rapidex.rapidex_android_app.ui.theme.surfaceContainerHighestLightMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.surfaceContainerLight
import com.rapidex.rapidex_android_app.ui.theme.surfaceContainerLightHighContrast
import com.rapidex.rapidex_android_app.ui.theme.surfaceContainerLightMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.surfaceContainerLowDark
import com.rapidex.rapidex_android_app.ui.theme.surfaceContainerLowDarkHighContrast
import com.rapidex.rapidex_android_app.ui.theme.surfaceContainerLowDarkMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.surfaceContainerLowLight
import com.rapidex.rapidex_android_app.ui.theme.surfaceContainerLowLightHighContrast
import com.rapidex.rapidex_android_app.ui.theme.surfaceContainerLowLightMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.surfaceContainerLowestDark
import com.rapidex.rapidex_android_app.ui.theme.surfaceContainerLowestDarkHighContrast
import com.rapidex.rapidex_android_app.ui.theme.surfaceContainerLowestDarkMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.surfaceContainerLowestLight
import com.rapidex.rapidex_android_app.ui.theme.surfaceContainerLowestLightHighContrast
import com.rapidex.rapidex_android_app.ui.theme.surfaceContainerLowestLightMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.surfaceDark
import com.rapidex.rapidex_android_app.ui.theme.surfaceDarkHighContrast
import com.rapidex.rapidex_android_app.ui.theme.surfaceDarkMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.surfaceDimDark
import com.rapidex.rapidex_android_app.ui.theme.surfaceDimDarkHighContrast
import com.rapidex.rapidex_android_app.ui.theme.surfaceDimDarkMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.surfaceDimLight
import com.rapidex.rapidex_android_app.ui.theme.surfaceDimLightHighContrast
import com.rapidex.rapidex_android_app.ui.theme.surfaceDimLightMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.surfaceLight
import com.rapidex.rapidex_android_app.ui.theme.surfaceLightHighContrast
import com.rapidex.rapidex_android_app.ui.theme.surfaceLightMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.surfaceVariantDark
import com.rapidex.rapidex_android_app.ui.theme.surfaceVariantDarkHighContrast
import com.rapidex.rapidex_android_app.ui.theme.surfaceVariantDarkMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.surfaceVariantLight
import com.rapidex.rapidex_android_app.ui.theme.surfaceVariantLightHighContrast
import com.rapidex.rapidex_android_app.ui.theme.surfaceVariantLightMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.tertiaryContainerDark
import com.rapidex.rapidex_android_app.ui.theme.tertiaryContainerDarkHighContrast
import com.rapidex.rapidex_android_app.ui.theme.tertiaryContainerDarkMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.tertiaryContainerLight
import com.rapidex.rapidex_android_app.ui.theme.tertiaryContainerLightHighContrast
import com.rapidex.rapidex_android_app.ui.theme.tertiaryContainerLightMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.tertiaryDark
import com.rapidex.rapidex_android_app.ui.theme.tertiaryDarkHighContrast
import com.rapidex.rapidex_android_app.ui.theme.tertiaryDarkMediumContrast
import com.rapidex.rapidex_android_app.ui.theme.tertiaryLight
import com.rapidex.rapidex_android_app.ui.theme.tertiaryLightHighContrast
import com.rapidex.rapidex_android_app.ui.theme.tertiaryLightMediumContrast

private val lightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
)

private val mediumContrastLightColorScheme = lightColorScheme(
    primary = primaryLightMediumContrast,
    onPrimary = onPrimaryLightMediumContrast,
    primaryContainer = primaryContainerLightMediumContrast,
    onPrimaryContainer = onPrimaryContainerLightMediumContrast,
    secondary = secondaryLightMediumContrast,
    onSecondary = onSecondaryLightMediumContrast,
    secondaryContainer = secondaryContainerLightMediumContrast,
    onSecondaryContainer = onSecondaryContainerLightMediumContrast,
    tertiary = tertiaryLightMediumContrast,
    onTertiary = onTertiaryLightMediumContrast,
    tertiaryContainer = tertiaryContainerLightMediumContrast,
    onTertiaryContainer = onTertiaryContainerLightMediumContrast,
    error = errorLightMediumContrast,
    onError = onErrorLightMediumContrast,
    errorContainer = errorContainerLightMediumContrast,
    onErrorContainer = onErrorContainerLightMediumContrast,
    background = backgroundLightMediumContrast,
    onBackground = onBackgroundLightMediumContrast,
    surface = surfaceLightMediumContrast,
    onSurface = onSurfaceLightMediumContrast,
    surfaceVariant = surfaceVariantLightMediumContrast,
    onSurfaceVariant = onSurfaceVariantLightMediumContrast,
    outline = outlineLightMediumContrast,
    outlineVariant = outlineVariantLightMediumContrast,
    scrim = scrimLightMediumContrast,
    inverseSurface = inverseSurfaceLightMediumContrast,
    inverseOnSurface = inverseOnSurfaceLightMediumContrast,
    inversePrimary = inversePrimaryLightMediumContrast,
    surfaceDim = surfaceDimLightMediumContrast,
    surfaceBright = surfaceBrightLightMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestLightMediumContrast,
    surfaceContainerLow = surfaceContainerLowLightMediumContrast,
    surfaceContainer = surfaceContainerLightMediumContrast,
    surfaceContainerHigh = surfaceContainerHighLightMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestLightMediumContrast,
)

private val highContrastLightColorScheme = lightColorScheme(
    primary = primaryLightHighContrast,
    onPrimary = onPrimaryLightHighContrast,
    primaryContainer = primaryContainerLightHighContrast,
    onPrimaryContainer = onPrimaryContainerLightHighContrast,
    secondary = secondaryLightHighContrast,
    onSecondary = onSecondaryLightHighContrast,
    secondaryContainer = secondaryContainerLightHighContrast,
    onSecondaryContainer = onSecondaryContainerLightHighContrast,
    tertiary = tertiaryLightHighContrast,
    onTertiary = onTertiaryLightHighContrast,
    tertiaryContainer = tertiaryContainerLightHighContrast,
    onTertiaryContainer = onTertiaryContainerLightHighContrast,
    error = errorLightHighContrast,
    onError = onErrorLightHighContrast,
    errorContainer = errorContainerLightHighContrast,
    onErrorContainer = onErrorContainerLightHighContrast,
    background = backgroundLightHighContrast,
    onBackground = onBackgroundLightHighContrast,
    surface = surfaceLightHighContrast,
    onSurface = onSurfaceLightHighContrast,
    surfaceVariant = surfaceVariantLightHighContrast,
    onSurfaceVariant = onSurfaceVariantLightHighContrast,
    outline = outlineLightHighContrast,
    outlineVariant = outlineVariantLightHighContrast,
    scrim = scrimLightHighContrast,
    inverseSurface = inverseSurfaceLightHighContrast,
    inverseOnSurface = inverseOnSurfaceLightHighContrast,
    inversePrimary = inversePrimaryLightHighContrast,
    surfaceDim = surfaceDimLightHighContrast,
    surfaceBright = surfaceBrightLightHighContrast,
    surfaceContainerLowest = surfaceContainerLowestLightHighContrast,
    surfaceContainerLow = surfaceContainerLowLightHighContrast,
    surfaceContainer = surfaceContainerLightHighContrast,
    surfaceContainerHigh = surfaceContainerHighLightHighContrast,
    surfaceContainerHighest = surfaceContainerHighestLightHighContrast,
)

private val mediumContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkMediumContrast,
    onPrimary = onPrimaryDarkMediumContrast,
    primaryContainer = primaryContainerDarkMediumContrast,
    onPrimaryContainer = onPrimaryContainerDarkMediumContrast,
    secondary = secondaryDarkMediumContrast,
    onSecondary = onSecondaryDarkMediumContrast,
    secondaryContainer = secondaryContainerDarkMediumContrast,
    onSecondaryContainer = onSecondaryContainerDarkMediumContrast,
    tertiary = tertiaryDarkMediumContrast,
    onTertiary = onTertiaryDarkMediumContrast,
    tertiaryContainer = tertiaryContainerDarkMediumContrast,
    onTertiaryContainer = onTertiaryContainerDarkMediumContrast,
    error = errorDarkMediumContrast,
    onError = onErrorDarkMediumContrast,
    errorContainer = errorContainerDarkMediumContrast,
    onErrorContainer = onErrorContainerDarkMediumContrast,
    background = backgroundDarkMediumContrast,
    onBackground = onBackgroundDarkMediumContrast,
    surface = surfaceDarkMediumContrast,
    onSurface = onSurfaceDarkMediumContrast,
    surfaceVariant = surfaceVariantDarkMediumContrast,
    onSurfaceVariant = onSurfaceVariantDarkMediumContrast,
    outline = outlineDarkMediumContrast,
    outlineVariant = outlineVariantDarkMediumContrast,
    scrim = scrimDarkMediumContrast,
    inverseSurface = inverseSurfaceDarkMediumContrast,
    inverseOnSurface = inverseOnSurfaceDarkMediumContrast,
    inversePrimary = inversePrimaryDarkMediumContrast,
    surfaceDim = surfaceDimDarkMediumContrast,
    surfaceBright = surfaceBrightDarkMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkMediumContrast,
    surfaceContainerLow = surfaceContainerLowDarkMediumContrast,
    surfaceContainer = surfaceContainerDarkMediumContrast,
    surfaceContainerHigh = surfaceContainerHighDarkMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkMediumContrast,
)

private val highContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkHighContrast,
    onPrimary = onPrimaryDarkHighContrast,
    primaryContainer = primaryContainerDarkHighContrast,
    onPrimaryContainer = onPrimaryContainerDarkHighContrast,
    secondary = secondaryDarkHighContrast,
    onSecondary = onSecondaryDarkHighContrast,
    secondaryContainer = secondaryContainerDarkHighContrast,
    onSecondaryContainer = onSecondaryContainerDarkHighContrast,
    tertiary = tertiaryDarkHighContrast,
    onTertiary = onTertiaryDarkHighContrast,
    tertiaryContainer = tertiaryContainerDarkHighContrast,
    onTertiaryContainer = onTertiaryContainerDarkHighContrast,
    error = errorDarkHighContrast,
    onError = onErrorDarkHighContrast,
    errorContainer = errorContainerDarkHighContrast,
    onErrorContainer = onErrorContainerDarkHighContrast,
    background = backgroundDarkHighContrast,
    onBackground = onBackgroundDarkHighContrast,
    surface = surfaceDarkHighContrast,
    onSurface = onSurfaceDarkHighContrast,
    surfaceVariant = surfaceVariantDarkHighContrast,
    onSurfaceVariant = onSurfaceVariantDarkHighContrast,
    outline = outlineDarkHighContrast,
    outlineVariant = outlineVariantDarkHighContrast,
    scrim = scrimDarkHighContrast,
    inverseSurface = inverseSurfaceDarkHighContrast,
    inverseOnSurface = inverseOnSurfaceDarkHighContrast,
    inversePrimary = inversePrimaryDarkHighContrast,
    surfaceDim = surfaceDimDarkHighContrast,
    surfaceBright = surfaceBrightDarkHighContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkHighContrast,
    surfaceContainerLow = surfaceContainerLowDarkHighContrast,
    surfaceContainer = surfaceContainerDarkHighContrast,
    surfaceContainerHigh = surfaceContainerHighDarkHighContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkHighContrast,
)

@Immutable
data class ColorFamily(
    val color: Color,
    val onColor: Color,
    val colorContainer: Color,
    val onColorContainer: Color
)

val unspecified_scheme = ColorFamily(
    Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified
)

@Composable
fun RapidexTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable() () -> Unit
) {
  val colorScheme = when {
      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
          val context = LocalContext.current
          if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }
      
      darkTheme -> darkScheme
      else -> lightScheme
  }

  MaterialTheme(
    colorScheme = colorScheme,
    typography = RapidexTypography,
    content = content
  )
}

