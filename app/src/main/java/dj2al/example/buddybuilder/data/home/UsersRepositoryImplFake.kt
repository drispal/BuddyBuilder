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

    override suspend fun addEventToUser(uId: String, eId: String): Resource<User> {
        val index = users.indexOfFirst { it.id == uId }
        users[index].subscribedEvents.add(eId)
        //println("Repo : ${users}")
        return Resource.Success(users[index])
    }

    override suspend fun addSportToUser(uId: String, sId: String): Resource<User> {
        val index = users.indexOfFirst { it.id == uId }
        users[index].subscribedSports.add(sId)
        //println("Repo : ${users}")
        return Resource.Success(users[index])
    }

    override suspend fun removeSportFromUser(uId: String, sId: String): Resource<User> {
        val indexUser = users.indexOfFirst { it.id == uId }
        val indexSport = users[indexUser].subscribedSports.indexOfFirst { it == sId }
        users[indexUser].subscribedSports.removeAt(indexSport)
        //println("Repo : ${users}")
        return Resource.Success(users[indexUser])
    }

    override suspend fun removeEventFromUser(uId: String, eId: String): Resource<User> {
        val indexUser = users.indexOfFirst { it.id == uId }
        val indexEvent = users[indexUser].subscribedEvents.indexOfFirst { it == eId }
        users[indexUser].subscribedEvents.removeAt(indexEvent)
        //println("Repo : ${users}")
        return Resource.Success(users[indexUser])
    }

}