package pl.daniel.services.data

import com.fasterxml.jackson.annotation.JsonProperty

data class Movie(
    val adult: Boolean,
    @JsonProperty("backdrop_path")
    val backdropPath: String,
    @JsonProperty("genre_ids")
    val genreIds: List<Long>,
    val id: Long,
    @JsonProperty("original_language")
    val originalLanguage: String,
    @JsonProperty("original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @JsonProperty("poster_path")
    val posterPath: String,
    @JsonProperty("release_date")
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    @JsonProperty("vote_average")
    val voteAverage: Double,
    @JsonProperty("vote_count")
    val voteCount: Long,
)
