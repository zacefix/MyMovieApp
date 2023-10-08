package pl.daniel.services.data

fun List<MovieDto>.parseToMovie(idFavorite: Long) = this.map {
    Movie(
        it.id,
        it.originalTitle,
        it.overview,
        it.posterPath,
        it.releaseDate,
        it.title,
        it.voteAverage,
        it.voteCount,
        it.id == idFavorite
    )
}