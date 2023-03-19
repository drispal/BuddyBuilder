package dj2al.example.buddybuilder.data.models

import android.media.Image
// import dj2al.example.buddybuilder.data.utils.currentDateTime
// a-t-on vrmt besoin de savoir quand il a été créé ou MAJ ?
//import com.google.firebase.firestore.Exclude
//import com.google.firebase.firestore.IgnoreExtraProperties

//@IgnoreExtraProperties
data class Sport(
    val id: String = "",
    val name: String = "",
    val thumbnail: Image,
    val eventsAvailable: List<Event> = listOf(),
) : BaseModel()

