package dj2al.example.buddybuilder.data.models

// "import dj2al.example.buddybuilder.data.utils.currentDateTime"    a-t-on vrmt besoin de savoir quand il a été créé ou MAJ ?
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class Sport(
    val name: String = "",
    val autre_variable_d_instance: String = ""
) : BaseModel()
