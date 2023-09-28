package pl.daniel.services.data

import com.fasterxml.jackson.annotation.JsonProperty

data class NowPlayingDto(
    val dates: RangeDate,
    val page: Long,
    val results: List<Movie>,
    @JsonProperty("total_pages")
    val totalPages: Long,
    @JsonProperty("total_results")
    val totalResults: Long,
)