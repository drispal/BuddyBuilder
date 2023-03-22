package dj2al.example.buddybuilder.data.models

//import com.google.firebase.firestore.IgnoreExtraProperties
import java.time.LocalDateTime

//@IgnoreExtraProperties
data class Event(
    val sport: String,
    val startTime: Long,
    val endTime: Long,
    val minParticipants: Int=0,
    val maxParticipants: Int=1,
    val level: Level,
    val nbParticipants: Int=0,
    val court: String,
    val responsable: String
) : BaseModel()
