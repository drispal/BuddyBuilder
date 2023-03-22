package dj2al.example.buddybuilder.data.models

import android.location.Location

data class User(
    val mail: String = "",
    val location: String, //Until we figure out how to use Location
    val maxDistance: Int = 0,
    val subscribedSports: MutableList<String> = mutableListOf(),
    val subscribedEvents: MutableList<String> = mutableListOf(),
) : BaseModel()