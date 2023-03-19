package dj2al.example.buddybuilder.data.home

import dj2al.example.buddybuilder.data.Resource
import dj2al.example.buddybuilder.data.models.Sport
import dj2al.example.buddybuilder.data.utils.currentDateTime

class SportsRepositoryImplEasy : SportsRepository {

    val sports : MutableList<Sport> = mutableListOf()

    override suspend fun getSports(): Resource<List<Sport>> {
        return Resource.Success(sports)
    }

    override suspend fun addSport(sport: Sport): Resource<Sport> {
        sports.add(sport)
        return Resource.Success(sport)
    }

    override suspend fun updateSport(sport: Sport): Resource<Sport> {
        sport.updatedAt = currentDateTime
        val index = sports.indexOfFirst { it.id == sport.id }
        sports[index] = sport
        return Resource.Success(sport)
    }

    override suspend fun deleteSport(id: String): Resource<Boolean> {
        val index = sports.indexOfFirst { it.id == id }
        sports.removeAt(index)
        return Resource.Success(true)
    }
}