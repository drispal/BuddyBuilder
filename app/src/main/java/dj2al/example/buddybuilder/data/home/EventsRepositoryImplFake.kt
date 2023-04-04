package dj2al.example.buddybuilder.data.home

import dj2al.example.buddybuilder.data.Resource
import dj2al.example.buddybuilder.data.models.Event
import dj2al.example.buddybuilder.data.models.Sport
import dj2al.example.buddybuilder.data.models.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class EventsRepositoryImplFake @Inject constructor(
    private val usersRepository: UsersRepositoryImplFake
    ): EventsRepository {

    private val events : MutableList<Event> = mutableListOf()

    override suspend fun getUserEvents(): Resource<List<Event>> {
        val userEvents = events.filter { usersRepository.users[0].subscribedEvents.contains(it.id) }
        return Resource.Success(userEvents)
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