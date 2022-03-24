package com.haberturm.tutuinternship.ui.detailScreen

sealed class DetailEvent {
    object OnNavigateUpClicked : DetailEvent()
    data class OnExpandClicked(val key: String) : DetailEvent()
    object RefreshData : DetailEvent()
}

const val EXPAND_POWER_KEY = "EXPAND_POWER"
const val EXPAND_APPEARANCE_KEY = "EXPAND_APPEARANCE"
