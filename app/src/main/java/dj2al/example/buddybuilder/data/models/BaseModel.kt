package dj2al.example.buddybuilder.data.models

import dj2al.example.buddybuilder.data.utils.currentDateTime
import com.google.firebase.firestore.Exclude
import java.util.UUID

abstract class BaseModel(
    @get:Exclude
    open var id: String = ""
) {
    var createdAt: Long = currentDateTime
    var updatedAt: Long = currentDateTime
}

