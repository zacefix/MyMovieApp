package pl.daniel.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PreferencesDataStoreServiceImpl @Inject constructor(@ApplicationContext private val context: Context) :
    PreferencesDataStoreService {
    private val Context.preferencesDataStore: DataStore<Preferences> by preferencesDataStore(
        name = "my_movie_app"
    )

    companion object {
        private val FAVORITE_MOVIE = longPreferencesKey("favorite_movie")
    }

    override suspend fun getFavoriteMovie(): Flow<Long> =
        context.preferencesDataStore.data.map { it[FAVORITE_MOVIE] ?: 0L }


    override suspend fun setFavoriteMovie(idMovie: Long) {
        context.preferencesDataStore.edit {
            it[FAVORITE_MOVIE] = idMovie
        }
    }


}

