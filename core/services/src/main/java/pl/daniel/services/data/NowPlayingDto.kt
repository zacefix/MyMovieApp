package pl.daniel.services.data

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class NowPlayingDto
@JsonCreator(mode = JsonCreator.Mode.PROPERTIES) constructor(
    @JsonProperty("dates")
    val dates: RangeDate,
    @JsonProperty("page")
    val page: Long,
    @JsonProperty("results")
    val movies: List<Movie>,
    @JsonProperty("total_pages")
    val totalPages: Long,
    @JsonProperty("total_results")
    val totalResults: Long
)