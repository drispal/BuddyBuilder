package dj2al.example.buddybuilder.data.models

import dj2al.example.buddybuilder.data.utils.currentDateTime // same commentaire que dans Sport.kt
import com.google.firebase.firestore.Exclude

abstract class BaseModel(
    @get:Exclude
    open var id: String = ""
) {
    var createdAt: Long = currentDateTime
    var updatedAt: Long = currentDateTime
}
