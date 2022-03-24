package com.haberturm.tutuinternship.ui.detailScreen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import coil.compose.AsyncImage
import com.haberturm.tutuinternship.R
import com.haberturm.tutuinternship.data.DataState
import com.haberturm.tutuinternship.ui.model.HeroUi
import com.haberturm.tutuinternship.ui.nav.NavRoute
import com.haberturm.tutuinternship.ui.nav.getOrThrow
import com.haberturm.tutuinternship.ui.view.ErrorView
import com.haberturm.tutuinternship.ui.view.LoadingScreen
import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties

const val KEY_CONTENT_PAGE_INDEX = "CONTENT_PAGE_INDEX"

/**
 * Every screen has a route, so that we don't have to add the route setup of all screens to the [NavigationComponent].
 *
 * Inherits NavRoute, to be able to navigate away from this screen. All navigation logic is in there.
 */
object DetailScreenRoute : NavRoute<DetailScreenViewModel> {

    override val route = "detail/{$KEY_CONTENT_PAGE_INDEX}/"

    /**
     * Returns the route that can be used for navigating to this page.
     */
    fun get(index: Int): String = route.replace("{$KEY_CONTENT_PAGE_INDEX}", "$index")

    fun getIndexFrom(savedStateHandle: SavedStateHandle) =
        savedStateHandle.getOrThrow<Int>(KEY_CONTENT_PAGE_INDEX)

    override fun getArguments(): List<NamedNavArgument> = listOf(
        navArgument(KEY_CONTENT_PAGE_INDEX) { type = NavType.IntType })

    @Composable
    override fun viewModel(): DetailScreenViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: DetailScreenViewModel) = DetailScreen(viewModel)
}

@Composable
private fun DetailScreen(
    viewModel: DetailScreenViewModel
) {
    Column(
        modifier = Modifier.padding(8.dp),
    ) {
        when (val dataState = viewModel.detailDataState) {
            is DataState.Success -> {
                val hero = (dataState.data as DetailUiModel).hero
                val powerstats = (dataState.data as DetailUiModel).powerstats
                val appearance = (dataState.data as DetailUiModel).appearance
                MainDetail(
                    hero = hero,
                    action = { viewModel.onEvent(DetailEvent.OnNavigateUpClicked) }
                )
                AdditionalDetails(
                    items = powerstats,
                    expandAction = {
                        viewModel.onEvent(
                            DetailEvent.OnExpandClicked(EXPAND_POWER_KEY)
                        )
                    },
                    isExpanded = viewModel.expandPowerState,
                    header = "Powerstats:"
                )
                AdditionalDetails(
                    items = appearance,
                    expandAction = {
                        viewModel.onEvent(
                            DetailEvent.OnExpandClicked(EXPAND_APPEARANCE_KEY)
                        )
                    },
                    isExpanded = viewModel.expandAppearanceState,
                    header = "Appearance:"
                )
            }
            is DataState.Failure -> {
                ErrorView(
                    action = { viewModel.onEvent(DetailEvent.RefreshData) },
                    text = stringResource(id = R.string.something_went_wrong)
                )
            }
            is DataState.Loading ->{
                LoadingScreen()
            }
            else -> {
            }
        }

    }
}

@Composable
fun MainDetail(
    hero: HeroUi,
    action: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        elevation = 10.dp,
    ) {
        Column() {
            IconButton(
                onClick = { action() }
            ) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.ic_baseline_arrow_back_24,
                    ),
                    contentDescription = "back",
                    tint = MaterialTheme.colors.secondaryVariant
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(8.dp)
            ) {
                AsyncImage(
                    model = hero.image,
                    contentDescription = "hero-icon",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .border(2.dp, MaterialTheme.colors.secondaryVariant, CircleShape)
                )
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                ) {
                    Text(
                        text = hero.name,
                        fontSize = 30.sp
                    )
                    Text(
                        text = hero.fullName,
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}

@Composable
inline fun <reified T : Any> AdditionalDetails(
    items: T,
    noinline expandAction: () -> Unit,
    isExpanded: Boolean,
    header: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        elevation = 10.dp,
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = header,
                    fontSize = 20.sp,
                    modifier = Modifier.weight(1f),
                    fontWeight = FontWeight.Bold
                )
                IconButton(
                    onClick = { expandAction() },
                ) {
                    if (isExpanded) {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.ic_baseline_expand_less_24,
                            ),
                            contentDescription = "expand-less",
                            tint = MaterialTheme.colors.secondaryVariant
                        )
                    } else {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.ic_baseline_expand_more_24,
                            ),
                            contentDescription = "expand-more",
                            tint = MaterialTheme.colors.secondaryVariant
                        )
                    }
                }
            }
            if (isExpanded) {
                val reflection: KClass<T> = T::class
                for (prop in reflection.memberProperties) {
                    Text(
                        text = "${prop.name}: ${prop.get(items)}",
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}


