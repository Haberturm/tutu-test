package com.haberturm.tutuinternship.ui.detailScreen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.haberturm.tutuinternship.data.DataState
import com.haberturm.tutuinternship.data.repositories.detailScreen.DetailRepository
import com.haberturm.tutuinternship.ui.model.AppearanceUi
import com.haberturm.tutuinternship.ui.model.HeroUi
import com.haberturm.tutuinternship.ui.model.PowerstatsUi
import com.haberturm.tutuinternship.ui.nav.RouteNavigator
import com.haberturm.tutuinternship.util.Util.toAppearanceUi
import com.haberturm.tutuinternship.util.Util.toHeroUi
import com.haberturm.tutuinternship.util.Util.toPowerstatsUi
import dagger.hilt.android.lifecycle.HiltViewModel
import hero.herodb.Appearance
import hero.herodb.HeroEntity
import hero.herodb.Powerstats
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

data class DetailUiModel(
    val hero: HeroUi,
    val appearance: AppearanceUi,
    val powerstats: PowerstatsUi
)

object DetailException {
    const val HERO_DOESNT_EXIST = "HERO DOESNT EXIST"
}

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val routeNavigator: RouteNavigator,
    private val repository: DetailRepository
) : ViewModel(), RouteNavigator by routeNavigator { // prefer delegation over inheritance

    private val heroId = DetailScreenRoute.getIndexFrom(savedStateHandle)
    var detailDataState by mutableStateOf<DataState>(DataState.Empty)
        private set

    init {
        viewModelScope.launch {
            try {
                detailDataState = DataState.Success(
                    data = DetailUiModel(
                        hero = repository.getHeroById(heroId)?.toHeroUi()
                            ?: throw Exception(DetailException.HERO_DOESNT_EXIST),
                        appearance = repository.getHeroAppearanceById(heroId)?.toAppearanceUi()
                            ?: throw Exception(DetailException.HERO_DOESNT_EXIST),
                        powerstats = repository.getHeroPowerstatsById(heroId)?.toPowerstatsUi()
                            ?: throw Exception(DetailException.HERO_DOESNT_EXIST)
                    )
                )

            } catch (e: Exception) {
                Log.i("DetailException1", "$e")
                withContext(Dispatchers.Main) {  //because of snapshot problem
                    detailDataState = DataState.Failure(e)
                }

            }
        }
    }

    fun onEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.OnNavigateUpClicked -> {
                navigateUp()
            }
        }
    }

}