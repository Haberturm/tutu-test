package com.haberturm.tutuinternship.data

sealed class DataState {
    object Loading : DataState()
    data class Failure(val e: Throwable) : DataState()
    data class Success(val data: Any) : DataState()
    data class Offline(val data: Any) : DataState()
    object Empty : DataState()
}