package dj2al.example.buddybuilder.data.models

import android.os.Parcelable
import dj2al.example.buddybuilder.data.utils.currentDateTime
import com.google.firebase.firestore.Exclude
import kotlinx.parcelize.Parcelize
import java.util.UUID

abstract class BaseModel(
    @get:Exclude
    open var id: String = ""
) : Parcelable {
    var createdAt: Long = currentDateTime
    var updatedAt: Long = currentDateTime
}

