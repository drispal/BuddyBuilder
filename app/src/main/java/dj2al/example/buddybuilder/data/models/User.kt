package dj2al.example.buddybuilder.data.models

import android.location.Location

data class User(
    val id: String = "",
    val mail: String = "",
    val password: String = "", // en attendant firebase
    val location: Location,
    val maxDistance: Int=0,
    val subscribedSports: List<Sport> = listOf(),
    val subscribedEvents: List<Event> = listOf(),
) : BaseModel()