package dj2al.example.buddybuilder.data.home

import dj2al.example.buddybuilder.data.Resource
import dj2al.example.buddybuilder.data.models.Event
import dj2al.example.buddybuilder.data.models.User

interface UsersRepository {
    suspend fun getUser(): Resource<User>
    suspend fun getAllUsers() : Resource<List<User>>
    suspend fun addUser(id : String, u : User): Resource<List<User>>
    suspend fun updateUser(u : User): Resource<User>
    suspend fun deleteUser(id : String): Resource<Boolean>
    suspend fun addSportToUser(sId: String): Resource<User>
    suspend fun removeSportFromUser(sId: String): Resource<User>
    suspend fun isSubscribedToEvent(eId: String): Boolean
    suspend fun isResponsibleForEvent(e: Event): Boolean
    suspend fun addEventToUser(eId: String): Resource<User>
    suspend fun removeEventFromUser(eId: String): Resource<User>
}