package com.resman.mycity.ui

import androidx.lifecycle.ViewModel
import com.resman.mycity.data.LocalCategoryDataProvider
import com.resman.mycity.model.Category
import com.resman.mycity.model.Site
import com.resman.mycity.util.ScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
class CityViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(
        CityUiState(
            categoryList = LocalCategoryDataProvider.getCategory(),
            currentCategory = LocalCategoryDataProvider.defaultCategory
        )
    )
    val uiState: StateFlow<CityUiState> = _uiState

    fun updateCurrentCategory(selectedCategory: Category) {
        _uiState.update { currentState ->
            currentState.copy(currentCategory = selectedCategory)
        }
    }
    fun navigateToCategoryPage() {
        _uiState.update { it.copy(screenState = ScreenState.CATEGORY) }
    }
    fun navigateToSitesPage() {
        _uiState.update { it.copy(screenState = ScreenState.SITES) }
    }
    fun navigateToDetailSitePage(site: Site) {
        _uiState.update { it.copy(currentSite = site, screenState = ScreenState.DETAILSITE) }
    }
    fun onBackPressed() {
        when(_uiState.value.screenState) {
            ScreenState.DETAILSITE -> navigateToSitesPage()
            ScreenState.SITES -> navigateToCategoryPage()
            ScreenState.CATEGORY -> closeApp()
        }
    }
    private fun closeApp() {
        _uiState.update { currentState ->
            currentState.copy(shouldCloseApp = true)
        }
    }
    fun selectSite(site: Site) {
        _uiState.update { currentState ->
            currentState.copy(currentSite = site)
        }
    }
}
data class CityUiState(
    val categoryList: List<Category> = emptyList(),
    val currentCategory: Category = LocalCategoryDataProvider.defaultCategory,
    val currentSite: Site = LocalCategoryDataProvider.defaultCategory.listSites[0],
    val showBack: Boolean = false,
    val screenState: ScreenState = ScreenState.CATEGORY,
    val shouldCloseApp: Boolean = false
)
