package pl.daniel.services

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pl.daniel.services.data.NowPlayingDto
import retrofit2.HttpException
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val moviesService: MoviesService
) {
    suspend fun loadNowPlaying(): Result<NowPlayingDto> {

        return withContext(Dispatchers.IO) {
            try {
                Result.success(moviesService.nowPlaying())
            } catch (exception: HttpException) {
                Result.failure(handleErrorMessage(exception))
            } catch (exception: Exception) {
                Result.failure(exception)
            }
        }

    }

}


