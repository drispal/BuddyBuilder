package dj2al.example.buddybuilder.data.models

//import com.google.firebase.firestore.IgnoreExtraProperties
import java.time.LocalDateTime

//@IgnoreExtraProperties
data class Event(
    val id: String = "",
    val sport: Sport,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val minParticipants: Int=0,
    val maxParticipants: Int=1,
    val level: Level,
    val nbParticipants: Int=1,
    val court: Court,
    val responsable: User
) : BaseModel()
