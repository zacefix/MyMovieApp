package pl.daniel.services

import pl.daniel.services.data.NowPlayingDto
import retrofit2.http.GET

interface MoviesService {

    @GET("movie/now_playing")
    suspend fun nowPlaying(): NowPlayingDto

}
