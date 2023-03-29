package dj2al.example.buddybuilder.data.home

import dj2al.example.buddybuilder.data.Resource
import dj2al.example.buddybuilder.data.models.Event

interface EventsRepository {
    suspend fun getUserEvents(): Resource<List<Event>>
    suspend fun getSportEvents(sportId : String): Resource<List<Event>>
    suspend fun getAllEvents(): Resource<List<Event>>
    suspend fun updateEvent(e: Event) : Resource<Event>
    suspend fun addEvent(e: Event) : Resource<Event>
    suspend fun deleteEvent(eventId: String) : Resource<Boolean>
}