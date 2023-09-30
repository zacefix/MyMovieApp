package pl.daniel.zar.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pl.daniel.datastore.PreferencesDataStoreService
import pl.daniel.services.MovieRepository
import pl.daniel.services.data.Movie
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val preferencesDataStoreService: PreferencesDataStoreService
) : ViewModel() {

    var isFavorite = MutableStateFlow(0L)
    var searchValue = MutableLiveData<String?>(null)
    var movies: MutableList<Movie> = mutableListOf()

    private val _movieState: MutableStateFlow<MovieState> =
        MutableStateFlow(MovieState.Start)
    val movieState: StateFlow<MovieState> = _movieState

    fun getMovies(page: Int = 1) {
        viewModelScope.launch {
            preferencesDataStoreService.getFavoriteMovie().collect { idFavorite ->
                _movieState.value = MovieState.Loading
                movieRepository.loadNowPlaying(page).onSuccess {
                    loadList(it.movies)
                    _movieState.value = MovieState.Success(it, idFavorite)
                }.onFailure { throwable ->
                    loadList(listOf())
                    _movieState.value = MovieState.Error(trouble = throwable.message.toString())
                }
            }
        }
    }

    private fun loadList(list: List<Movie>) {
        movies.addAll(list)
    }

    fun refreshMovies() {
        movies = mutableListOf()
        getMovies()
    }

}