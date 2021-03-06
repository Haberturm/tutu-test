package com.haberturm.tutuinternship.ui.listScreen

sealed class ListScreenEvent{
    object RefreshData : ListScreenEvent()
    object TryOfflineMode : ListScreenEvent()
    data class NavigateToDetailScreen(val itemId: Int) : ListScreenEvent()
}
