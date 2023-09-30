package pl.daniel.services

import pl.daniel.services.data.NowPlayingDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {

    @GET("movie/now_playing")
    suspend fun nowPlaying(@Query("page") page: Long): NowPlayingDto

}
