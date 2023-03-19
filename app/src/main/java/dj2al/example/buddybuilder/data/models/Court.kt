package dj2al.example.buddybuilder.data.models

import android.location.Location
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class Court(
    val name: String = "",
    val location: Location
) : BaseModel()