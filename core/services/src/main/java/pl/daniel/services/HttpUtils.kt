package pl.daniel.services

import org.json.JSONObject
import pl.daniel.services.HttpCodes.HTTP_400
import pl.daniel.services.HttpCodes.HTTP_500
import retrofit2.HttpException

object HttpCodes {
    const val HTTP_400 = 400
    const val HTTP_500 = 500
}

fun handleErrorMessage(exception: HttpException) =
    when {
        exception.code() in HTTP_400 until HTTP_500 -> {
            val errorJsonString = exception.response()?.errorBody()?.string()
            errorJsonString?.let {
                Throwable(
                    JSONObject(it).getString("message"),
                    cause = Throwable(JSONObject(it).getString("code"))
                )
            } ?: exception
        }

        else -> exception
    }


