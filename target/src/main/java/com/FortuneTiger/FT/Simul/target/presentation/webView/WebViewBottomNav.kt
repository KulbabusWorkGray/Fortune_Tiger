package com.FortuneTiger.FT.Simul.target.presentation.webView

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ColumnScope.WebViewBottomNav(
    canGoBack: Boolean,
    onBackPress: () -> Unit,
    canGoForward: Boolean,
    onForwardPress: () -> Unit,
    isLoading : Boolean = false
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .background(MaterialTheme.colorScheme.surfaceVariant),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        FilledIconButton(
            onClick = onBackPress,
            enabled = canGoBack
        ) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
        }
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1f),
            contentAlignment = Alignment.Center
        ) {
            if (isLoading) CircularProgressIndicator()
        }
        FilledIconButton(
            onClick = onForwardPress,
            enabled = canGoForward
        ) {
            Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null)
        }
    }
}