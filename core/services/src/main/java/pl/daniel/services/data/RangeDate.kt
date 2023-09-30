package pl.daniel.services.data

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class RangeDate
@JsonCreator(mode = JsonCreator.Mode.PROPERTIES) constructor(
    @JsonProperty("maximum")
    val maximum: String,
    @JsonProperty("minimum")
    val minimum: String
)
