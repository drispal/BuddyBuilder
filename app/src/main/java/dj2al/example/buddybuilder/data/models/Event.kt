package dj2al.example.buddybuilder.data.models

import com.google.firebase.firestore.IgnoreExtraProperties
import dj2al.example.buddybuilder.data.utils.currentDateTime
import kotlinx.parcelize.Parcelize

@Parcelize
@IgnoreExtraProperties
data class Event(
    val sportName : String = "",
    val sport: String = "",
    val startTime: Long = currentDateTime,
    val endTime: Long = currentDateTime,
    val minParticipants: Int = 1,
    val maxParticipants: Int = 1,
    val level: Int = 1,
    var nbParticipants: Int = 0,
    val court: String = "",
    val responsable: String = ""
) : BaseModel()

