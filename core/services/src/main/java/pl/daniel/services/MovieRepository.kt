package pl.daniel.services

import pl.daniel.services.data.NowPlayingDto
import retrofit2.HttpException
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val moviesService: MoviesService
) {
    suspend fun loadNowPlaying(page: Long): Result<NowPlayingDto> {

        return try {
            Result.success(moviesService.nowPlaying(page))
        } catch (exception: HttpException) {
            Result.failure(handleErrorMessage(exception))
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }

}


