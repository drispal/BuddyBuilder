package dj2al.example.buddybuilder.data.home

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dj2al.example.buddybuilder.data.BaseRepository
import dj2al.example.buddybuilder.data.Resource
import dj2al.example.buddybuilder.data.models.Event
import dj2al.example.buddybuilder.data.utils.await
import javax.inject.Inject

class EventsRepositoryImpl @Inject constructor(
    auth : FirebaseAuth,
    firestore: FirebaseFirestore,
    private val usersRepository: UsersRepositoryImpl
    ): EventsRepository, BaseRepository<Event>(auth) {

    protected val db = firestore.collection("events")

    override suspend fun getUserEvents(): Resource<List<Event>> {
        return try {
            val snapshot = db.get().await()
            val events = getData(snapshot, Event::class.java)
            usersRepository.getUser().let { user ->
                if (user is Resource.Success) {
                    val myEvents = events.filter { event ->
                        user.result.subscribedEvents.contains(event.id)
                    }
                    return Resource.Success(myEvents)
                } else {
                    throw Exception("Can't get user info's")
                }
            }
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }

    override suspend fun getSportEvents(sportId : String): Resource<List<Event>> {
        return try {
            val snapshot = db.get().await()
            val events = getData(snapshot, Event::class.java)
            val sportEvents = events.filter{ it.sport == sportId}
            return Resource.Success(sportEvents)
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }

    override suspend fun getAllEvents(): Resource<List<Event>> {
        return try {
            val snapshot = db.get().await()
            val events = getData(snapshot, Event::class.java)
            return Resource.Success(events)
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }

    override suspend fun updateEvent(e: Event): Resource<Event> {
        return try {
            db.document(e.id).set(e)
            val snapshot = db.document(e.id).get().await()
            Resource.Success(getDocumentModel(snapshot, Event::class.java) as Event)
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }

    override suspend fun addEvent(e: Event): Resource<Event> {
        return try {
            db.add(e)
            val snapshot = db.document(e.id).get().await()
            Resource.Success(getDocumentModel(snapshot, Event::class.java) as Event)
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }

    override suspend fun deleteEvent(eventId : String): Resource<Boolean> {
        return try {
            db.document(eventId).delete()
            Resource.Success(true)
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }
}