package com.haberturm.tutuinternship.data.network

import hero.herodb.HeroEntity

sealed class ApiState {
    object Loading : ApiState()
    data class Failure(val e: Throwable) : ApiState()
    data class Success(val data: Any) : ApiState()
    object Empty : ApiState()
}