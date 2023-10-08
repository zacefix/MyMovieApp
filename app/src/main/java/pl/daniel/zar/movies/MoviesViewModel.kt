package pl.daniel.zar.movies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pl.daniel.datastore.PreferencesDataStoreService
import pl.daniel.services.MovieRepository
import pl.daniel.services.data.Movie
import pl.daniel.services.data.parseToMovie
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val preferencesDataStoreService: PreferencesDataStoreService
) : ViewModel() {

    var searchValue = MutableLiveData<String?>(null)
    var movies: MutableList<Movie> = mutableListOf()
    private var page: Long = 0
    private var totalPages: Long = 1

    private val _movieState: MutableStateFlow<MovieState> =
        MutableStateFlow(MovieState.Start)
    val movieState: LiveData<MovieState> = _movieState.asLiveData()

    fun getMovies(fistPage: Boolean = true) {
        if (fistPage)
            page = 0
        if (page < totalPages)
            page++

        viewModelScope.launch {
            preferencesDataStoreService.getFavoriteMovie().collect { idFavorite ->
                _movieState.value = MovieState.Loading
                movieRepository.loadNowPlaying(page).onSuccess {
                    totalPages = it.totalPages
                    loadList(it.movies.parseToMovie(idFavorite))
                    _movieState.value = MovieState.Success(movies.toList())
                }.onFailure { throwable ->
                    loadList(listOf())
                    Log.wtf("ERRR", throwable.message.toString())
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