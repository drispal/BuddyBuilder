package dj2al.example.buddybuilder.data.home

import dj2al.example.buddybuilder.data.Resource
import dj2al.example.buddybuilder.data.models.Event
import dj2al.example.buddybuilder.data.models.Sport
import dj2al.example.buddybuilder.data.models.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class EventsRepositoryImplFake @Inject constructor(private val usersRepository: UsersRepositoryImplFake): EventsRepository {

    private val events : MutableList<Event> = mutableListOf()
    private val _user = MutableStateFlow<Resource<User>?>(null)
    val sports: StateFlow<Resource<User>?> = _user

    override suspend fun getUserEvents(): Resource<List<Event>> {
        _user.value = usersRepository.getUser()
        _user.value?.let { u ->
            when (u) {
                is Resource.Failure -> {
                    return u
                }
                is Resource.Loading -> {
                    return u
                }
                is Resource.Success -> {
                    val userEvents = events.filter { u.result.subscribedEvents.contains(it.id) }
                    return Resource.Success(userEvents)
                }
            }
        }
        return Resource.Failure(Exception("User not found"))
    }

    override suspend fun getSportEvents(sportId : String): Resource<List<Event>> {
        val sportEvents = events.filter{ it.sport == sportId}
        return Resource.Success(sportEvents)
    }

    override suspend fun getAllEvents(): Resource<List<Event>> {
        return Resource.Success(events)
    }

    override suspend fun updateEvent(e: Event): Resource<Event> {
        val index = events.indexOfFirst { it.id == e.id }
        events[index] = e
        return Resource.Success(events[index])
    }

    override suspend fun addEvent(e: Event): Resource<Event> {
        events.add(e)
        usersRepository.users[0].subscribedEvents.add(e.id)
        return Resource.Success(e)
    }

    override suspend fun deleteEvent(eventId : String): Resource<Boolean> {
        val index = events.indexOfFirst { it.id == eventId }
        events.removeAt(index)
        return Resource.Success(true)
    }
}