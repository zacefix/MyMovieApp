package pl.daniel.zar.movies

import pl.daniel.services.data.Movie

sealed class MovieState {
    object Start : MovieState()
    object Loading : MovieState()
    class Error(val trouble: String) : MovieState()
    class Success(val content: List<Movie>, val idFavorite: Long) : MovieState()
}