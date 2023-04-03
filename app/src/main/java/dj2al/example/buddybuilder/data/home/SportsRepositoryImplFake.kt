package dj2al.example.buddybuilder.data.home

import android.content.Context
import androidx.compose.ui.res.stringResource
import dagger.hilt.android.qualifiers.ApplicationContext
import dj2al.example.buddybuilder.R
import dj2al.example.buddybuilder.data.Resource
import dj2al.example.buddybuilder.data.models.Sport
import dj2al.example.buddybuilder.data.models.User
import dj2al.example.buddybuilder.data.utils.currentDateTime
import javax.inject.Inject



class SportsRepositoryImplFake @Inject constructor(@ApplicationContext private val context: Context): SportsRepository {

    private val sports : MutableList<Sport> = mutableListOf()

    init {
        sports.add(Sport(
            name = context.getString(R.string.soccer),
            thumbnail = R.drawable.sports_soccer,
        ))
        sports.add(Sport(
            name = context.getString(R.string.basketball),
            thumbnail = R.drawable.sports_basket,
        ))
        sports.add(Sport(
            name = context.getString(R.string.ski),
            thumbnail = R.drawable.sports_ski,
        ))
        sports.add(Sport(
            name = context.getString(R.string.swimming),
            thumbnail = R.drawable.sports_swim,
        ))
        sports.add(Sport(
            name = context.getString(R.string.volley),
            thumbnail = R.drawable.sports_volley,
        ))
        sports.add(Sport(
            name = context.getString(R.string.crossfit),
            thumbnail = R.drawable.sports_muscu,
        ))
        sports.add(Sport(
            name = context.getString(R.string.boxing),
            thumbnail = R.drawable.sports_boxe,
        ))

    }

    override suspend fun getSports(): Resource<List<Sport>> {
        return Resource.Success(sports)
    }

    override suspend fun addSport(sport: Sport): Resource<List<Sport>> {
        sports.add(sport)
        return Resource.Success(sports)
    }

    override suspend fun updateSport(sport: Sport): Resource<Sport> {
        sport.updatedAt = currentDateTime
        val index = sports.indexOfFirst { it.id == sport.id }
        sports[index] = sport
        return Resource.Success(sports[index])
    }

    override suspend fun deleteSport(id: String): Resource<Boolean> {
        val index = sports.indexOfFirst { it.id == id }
        sports.removeAt(index)
        return Resource.Success(true)
    }
}

