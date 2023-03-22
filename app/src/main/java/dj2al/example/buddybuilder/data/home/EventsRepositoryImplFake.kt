package dj2al.example.buddybuilder.data.home

import dj2al.example.buddybuilder.data.Resource
import dj2al.example.buddybuilder.data.models.Event
import javax.inject.Inject

class EventsRepositoryImplFake @Inject constructor(): EventsRepository {

    private val events : MutableList<Event> = mutableListOf()

    override suspend fun getEvent(id: String): Resource<Event> {
        val index = events.indexOfFirst{ it.id == id }
        return Resource.Success(events[index])
    }

    override suspend fun getAllEvents(): Resource<List<Event>> {
        return Resource.Success(events)
    }

    override suspend fun updateEvent(e: Event): Resource<Event> {
        val index = events.indexOfFirst { it.id == e.id }
        events[index] = e
        return Resource.Success(events[index])
    }

    override suspend fun addEvent(e: Event): Resource<List<Event>> {
        events.add(e)
        return Resource.Success(events)
    }

    override suspend fun deleteEvent(e: Event): Resource<Boolean> {
        val index = events.indexOfFirst { it.id == e.id }
        events.removeAt(index)
        return Resource.Success(true)
    }
}