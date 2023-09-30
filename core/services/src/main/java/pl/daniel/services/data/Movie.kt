package pl.daniel.services.data

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

data class Movie
@JsonCreator(mode = JsonCreator.Mode.PROPERTIES) constructor(
    @JsonProperty("adult")
    val adult: Boolean = false,
    @JsonProperty("backdrop_path")
    val backdropPath: String?,
    @JsonProperty("genre_ids")
    val genreIds: List<Long>,
    @JsonProperty("id")
    val id: Long,
    @JsonProperty("original_language")
    val originalLanguage: String,
    @JsonProperty("original_title")
    val originalTitle: String,
    @JsonProperty("overview")
    val overview: String,
    @JsonProperty("popularity")
    val popularity: Double,
    @JsonProperty("poster_path")
    val posterPath: String?,
    @JsonProperty("release_date")
    val releaseDate: String,
    @JsonProperty("title")
    val title: String,
    @JsonProperty("video")
    val video: Boolean,
    @JsonProperty("vote_average")
    val voteAverage: Double,
    @JsonProperty("vote_count")
    val voteCount: Long
) : Serializable
