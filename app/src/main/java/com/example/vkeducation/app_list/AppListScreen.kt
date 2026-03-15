package com.example.vkeducation.app_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vkeducation.R
import com.example.vkeducation.data.AppCard

@Composable
fun AppListScreen(
    modifier: Modifier = Modifier,
    onAppCardClicked: () -> Unit = {}
) {


    val viewModel: AppListViewModel = viewModel()
    val state: AppListState by viewModel.state.collectAsStateWithLifecycle()
    val events = viewModel.events
    val snackbarHostState = remember { SnackbarHostState() }

    val logoMessage = stringResource(R.string.rustore_logo_message)

    LaunchedEffect(Unit) {
        events.collect { event ->
            when (event) {
                is AppListEvent.RuStoreLogo -> {
                    snackbarHostState.showSnackbar(logoMessage)
                }
            }
        }
    }


    when (val currentState = state) {
        is AppListState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        }
        is AppListState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                    modifier = Modifier.size(60.dp)
                )
            }
        }
        is AppListState.Content -> {
            Scaffold(
                snackbarHost = {
                    SnackbarHost(snackbarHostState)
                },
                containerColor = Color(0xff0077FF),
                modifier = Modifier.fillMaxSize(),
                topBar = { AppListHeader { viewModel.showLogoMessage() } }
            ) { innerPadding ->

                LazyColumn(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                        .background(Color.White)
                        .padding(top = 16.dp)
                ) {
                    items(currentState.apps){ appCard ->
                        AppCard(appCard, onAppCardClicked)
                    }
                }
            }
        }
    }
}


