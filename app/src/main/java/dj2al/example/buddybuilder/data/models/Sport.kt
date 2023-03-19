package dj2al.example.buddybuilder.data.models

import android.media.Image
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class Sport(
    val name: String = "",
    val thumbnail: Image,
    val eventsAvailables: List<Event>,
) : BaseModel()

