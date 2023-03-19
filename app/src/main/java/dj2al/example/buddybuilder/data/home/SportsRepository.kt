package dj2al.example.buddybuilder.data.home

import dj2al.example.buddybuilder.data.Resource
import dj2al.example.buddybuilder.data.models.Sport

interface SportsRepository {
    suspend fun getSports(): Resource<List<Sport>>
    suspend fun addSport(sport: Sport): Resource<Sport>
    suspend fun updateSport(sport: Sport): Resource<Sport>
    suspend fun deleteSport(id: String): Resource<Boolean>
}
