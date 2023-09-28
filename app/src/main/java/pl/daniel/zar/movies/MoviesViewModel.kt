package pl.daniel.zar.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pl.daniel.services.MovieRepository
import pl.daniel.services.data.Movie
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    var searchValue = MutableLiveData<String?>(null)
    var movies: MutableList<Movie> = mutableListOf()

    fun getMovies(stackId: Int, page: Int) {
        viewModelScope.launch {
            movieRepository.loadNowPlaying(
            ).onSuccess {
                loadList(it.movies)

            }.onFailure { throwable ->
                loadList(listOf())
            }
        }
    }

    private fun loadList(list: List<Movie>) {
        movies.addAll(list)
    }

}