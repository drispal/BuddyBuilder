package dj2al.example.buddybuilder.data.home

import dj2al.example.buddybuilder.R
import dj2al.example.buddybuilder.data.Resource
import dj2al.example.buddybuilder.data.models.Sport
import dj2al.example.buddybuilder.data.utils.currentDateTime
import javax.inject.Inject


class SportsRepositoryImplFake @Inject constructor(): SportsRepository {

    private val sports : MutableList<Sport> = mutableListOf()

    init {
        sports.add(Sport(
            name = "Soccer",
            thumbnail = R.drawable.logo,
            eventsAvailables = mutableListOf()
        ))
        sports.add(Sport(
            name = "BasketBall",
            thumbnail = R.drawable.logo,
            eventsAvailables = mutableListOf()
        ))
        sports.add(Sport(
            name = "Hockey",
            thumbnail = R.drawable.logo,
            eventsAvailables = mutableListOf()
        ))
        sports.add(Sport(
            name = "Tennis",
            thumbnail = R.drawable.logo,
            eventsAvailables = mutableListOf()
        ))
        sports.add(Sport(
            name = "Volley",
            thumbnail = R.drawable.logo,
            eventsAvailables = mutableListOf()
        ))
        sports.add(Sport(
            name = "Crossfit",
            thumbnail = R.drawable.logo,
            eventsAvailables = mutableListOf()
        ))
        sports.add(Sport(
            name = "Running",
            thumbnail = R.drawable.logo,
            eventsAvailables = mutableListOf()
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

