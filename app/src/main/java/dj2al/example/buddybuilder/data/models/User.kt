package dj2al.example.buddybuilder.data.models

import android.location.Location

data class User(
    val mail: String = "",
    val location: Location,
    val maxDistance: Int = 0,
    val subscribedSports: List<Sport> = listOf(),
    val subscribedEvents: List<Event> = listOf(),
) : BaseModel()