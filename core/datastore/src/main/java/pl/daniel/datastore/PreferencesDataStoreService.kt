package pl.daniel.datastore

import kotlinx.coroutines.flow.Flow

interface PreferencesDataStoreService {
    suspend fun getFavoriteMovie(): Flow<Long>
    suspend fun setFavoriteMovie(idMovie: Long)
}