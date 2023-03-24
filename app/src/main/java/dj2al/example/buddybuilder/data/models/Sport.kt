package dj2al.example.buddybuilder.data.models

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class Sport(
    val name: String = "",
    val thumbnail: Int,
) : BaseModel()

