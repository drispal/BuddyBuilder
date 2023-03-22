package dj2al.example.buddybuilder.data.home

import dj2al.example.buddybuilder.data.Resource
import dj2al.example.buddybuilder.data.models.Event

interface EventRepository {
    suspend fun getEvent(id: String): Resource<Event>
    suspend fun getAllEvents(): Resource<List<Event>>
    suspend fun updateEvent(e: Event) : Resource<Event>
    suspend fun addEvent(e: Event) : Resource<List<Event>>
    suspend fun deleteEvent(e: Event) : Resource<Boolean>
}