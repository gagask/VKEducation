package com.example.vkeducation.presentation.applist

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
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vkeducation.R
import com.example.vkeducation.RuStoreApp
import com.example.vkeducation.ui.theme.VkEducationTheme

@Composable
fun AppListScreen(
    modifier: Modifier = Modifier,
    viewModel: AppListViewModel = hiltViewModel(),
    onAppCardClicked: () -> Unit = {}
) {

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
                containerColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier.fillMaxSize(),
                topBar = { AppListHeader { viewModel.showLogoMessage() } }
            ) { innerPadding ->

                LazyColumn(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                        .background(MaterialTheme.colorScheme.background)
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

@Preview
@Composable
fun AppListScreenPreview() {
    VkEducationTheme {
        RuStoreApp()
    }
}


