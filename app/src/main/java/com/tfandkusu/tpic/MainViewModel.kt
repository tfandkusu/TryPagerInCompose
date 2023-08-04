package com.tfandkusu.tpic

import androidx.lifecycle.ViewModel
import com.tfandkusu.tpic.model.YearMonth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        State(
            listOf(
               YearMonth(2023,8),
                YearMonth(2023,7),
            )
        )
    )

    val state: StateFlow<State> = _uiState

    fun onLastPageShow() {
        _uiState.value = state.value.copy(
            monthList = state.value.monthList + listOf(
                state.value.monthList.last().previous()
            )
        )
    }

    data class State(
        val monthList: List<YearMonth>,
    )
}