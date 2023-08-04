package com.tfandkusu.tpic.model

data class YearMonth(
    val year: Int,
    val month: Int
) {
    fun previous(): YearMonth {
        return if (month >= 2) {
            YearMonth(year = year, month = month - 1)
        }else {
            YearMonth(year = year - 1, month = 12)
        }
    }
}
