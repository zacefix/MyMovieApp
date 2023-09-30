package pl.daniel.zar.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import pl.daniel.datastore.PreferencesDataStoreService
import pl.daniel.services.data.Movie
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val preferencesDataStoreService: PreferencesDataStoreService
) : ViewModel() {

    var isFavorite = MutableStateFlow(false)
    var data = MutableLiveData<Movie>(null)

    fun setData(movie: Movie) {
        data.value = movie
        checkFavorite(movie.id)
    }

    private fun checkFavorite(id: Long) {
        viewModelScope.launch {
            val idFavorite = preferencesDataStoreService.getFavoriteMovie()
            idFavorite.collect {
                isFavorite.value = it == id
            }
        }
    }

    fun clickFabFavorite() {
        if (isFavorite.value) {
           clearFavorite()
        } else {
            data.value?.let { addToFavorite(it.id) }
        }
    }

    private fun addToFavorite(id: Long) {
        viewModelScope.launch {
            preferencesDataStoreService.setFavoriteMovie(id)
            isFavorite.value = true
        }
    }

    private fun clearFavorite() {
        viewModelScope.launch {
            preferencesDataStoreService.setFavoriteMovie(0)
            isFavorite.value = false
        }
    }
}