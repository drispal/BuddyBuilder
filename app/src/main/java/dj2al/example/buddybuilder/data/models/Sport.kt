package dj2al.example.buddybuilder.data.models

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
@kotlinx.parcelize.Parcelize
data class Sport(
    val name: String = "",
    val thumbnail: String = "",
) : BaseModel()

