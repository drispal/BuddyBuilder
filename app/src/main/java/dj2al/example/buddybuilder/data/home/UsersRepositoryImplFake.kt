package dj2al.example.buddybuilder.data.home

import dj2al.example.buddybuilder.data.Resource
import dj2al.example.buddybuilder.data.models.User
import dj2al.example.buddybuilder.data.utils.currentDateTime
import javax.inject.Inject

class UsersRepositoryImplFake @Inject constructor(): UsersRepository {
    private val users : MutableList<User> = mutableListOf()

    init {
        users.add(
            User(
            mail = "sarah@mail.com",
            location = "Paris",
            maxDistance = 5,
            subscribedSports = mutableListOf(),
            subscribedEvents = mutableListOf(),
        ))
        users.add(
            User(
                mail = "lucas@mail.com",
                location = "Lyon",
                maxDistance = 10,
                subscribedSports = mutableListOf(),
                subscribedEvents = mutableListOf(),
            ))
    }

    override suspend fun getUser(): Resource<User> {
        return Resource.Success(users[0])
    }

    override suspend fun getAllUsers(): Resource<List<User>> {
        return Resource.Success(users)
    }

    override suspend fun addUser(u : User): Resource<List<User>> {
        users.add(u)
        return Resource.Success(users)
    }

    override suspend fun updateUser(u : User): Resource<User> {
        u.updatedAt = currentDateTime
        val index = users.indexOfFirst { it.id == u.id }
        users[index] = u
        return Resource.Success(users[index])
    }

    override suspend fun deleteUser(id : String): Resource<Boolean> {
        val index = users.indexOfFirst { it.id == id }
        users.removeAt(index)
        return Resource.Success(true)
    }

    override suspend fun addEventToUser(uId: String, eId: String): Resource<User> {
        val index = users.indexOfFirst { it.id == uId }
        users[index].subscribedEvents.add(eId)
        return Resource.Success(users[index])
    }

    override suspend fun addSportToUser(uId: String, sId: String): Resource<User> {
        val index = users.indexOfFirst { it.id == uId }
        users[index].subscribedEvents.add(sId)
        return Resource.Success(users[index])
    }


}