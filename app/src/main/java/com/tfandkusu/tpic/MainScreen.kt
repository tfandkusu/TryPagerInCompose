package com.tfandkusu.tpic

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tfandkusu.tpic.model.YearMonth
import com.tfandkusu.tpic.ui.theme.MyTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    state: MainViewModel.State,
    onLastPageShow: () -> Unit,
) {
    val pagerState = rememberPagerState()
    LaunchedEffect(Unit) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            if (page >= state.monthList.size - 1) {
                onLastPageShow()
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(R.string.app_name))
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                ),
            )
        },
    ) { padding ->
        HorizontalPager(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth(),
            state = pagerState,
            pageCount = state.monthList.size,
            reverseLayout = true
        ) { page ->
            val month = state.monthList[page]
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = "%04d/%02d".format(month.year, month.month),
                    style = MaterialTheme.typography.headlineLarge,
                )
            }
        }
    }
}

@Composable
@Preview
private fun Preview() {
    MyTheme {
        MainScreen(
            MainViewModel.State(
                monthList = listOf(
                    YearMonth(year = 2023, month = 3),
                    YearMonth(year = 2023, month = 2),
                    YearMonth(year = 2023, month = 1),
                )
            ),
            onLastPageShow = {}
        )
    }
}
