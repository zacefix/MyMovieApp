package pl.daniel.zar.movies

import pl.daniel.services.data.NowPlayingDto

sealed class MovieState {
    object Start : MovieState()
    object Loading : MovieState()
    class Error(val trouble: String) : MovieState()
    class Success(val content: NowPlayingDto, val idFavorite: Long) : MovieState()
}