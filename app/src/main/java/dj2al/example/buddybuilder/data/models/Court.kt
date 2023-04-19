package dj2al.example.buddybuilder.data.models

import android.location.Location
import com.google.firebase.firestore.IgnoreExtraProperties
import kotlinx.parcelize.Parcelize

@IgnoreExtraProperties
@Parcelize
data class Court(
    val name: String = "",
    val location: Location
) : BaseModel()