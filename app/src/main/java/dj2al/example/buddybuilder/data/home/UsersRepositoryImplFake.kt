package dj2al.example.buddybuilder.data.home

import dj2al.example.buddybuilder.data.Resource
import dj2al.example.buddybuilder.data.models.User
import dj2al.example.buddybuilder.data.utils.currentDateTime
import javax.inject.Inject

class UsersRepositoryImplFake @Inject constructor(): UsersRepository {
    val users : MutableList<User> = mutableListOf()

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
        //println("Repo : ${users}")
        return Resource.Success(users[0])
    }

    override suspend fun getAllUsers(): Resource<List<User>> {
        //println("Repo : ${users}")
        return Resource.Success(users)
    }

    override suspend fun addUser(u : User): Resource<List<User>> {
        users.add(u)
        //println("Repo : ${users}")
        return Resource.Success(users)
    }

    override suspend fun updateUser(u : User): Resource<User> {
        u.updatedAt = currentDateTime
        val index = users.indexOfFirst { it.id == u.id }
        users[index] = u
        //println("Repo : ${users}")
        return Resource.Success(users[index])
    }

    override suspend fun deleteUser(id : String): Resource<Boolean> {
        val index = users.indexOfFirst { it.id == id }
        users.removeAt(index)
        //println("Repo : ${users}")
        return Resource.Success(true)
    }

    override suspend fun addEventToUser(eId: String): Resource<User> {
        users[0].subscribedEvents.add(eId)
        //println("Repo : ${users}")
        return Resource.Success(users[0])
    }

    override suspend fun addSportToUser(sId: String): Resource<User> {
        users[0].subscribedSports.add(sId)
        //println("Repo : ${users}")
        return Resource.Success(users[0])
    }

    override suspend fun removeSportFromUser(sId: String): Resource<User> {
        val indexSport = users[0].subscribedSports.indexOfFirst { it == sId }
        users[0].subscribedSports.removeAt(indexSport)
        //println("Repo : ${users}")
        return Resource.Success(users[0])
    }

    override suspend fun removeEventFromUser(eId: String): Resource<User> {
        val indexEvent = users[0].subscribedEvents.indexOfFirst { it == eId }
        users[0].subscribedEvents.removeAt(indexEvent)
        //println("Repo : ${users}")
        return Resource.Success(users[0])
    }

}