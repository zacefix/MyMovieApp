package pl.daniel.services.data

import java.io.Serializable

data class Movie(
    val id: Long,
    val originalTitle: String,
    val overview: String,
    val posterPath: String?,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    val voteCount: Long,
    val favorite: Boolean
) : Serializable

