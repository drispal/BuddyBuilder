package dj2al.example.buddybuilder.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import dj2al.example.buddybuilder.data.models.BaseModel

abstract class BaseRepository<T : BaseModel>(
    auth: FirebaseAuth,
) {
    private val currentUser = auth.currentUser

    fun getData(snapshot: QuerySnapshot, model: Class<T>): List<T> {
        return snapshot.map {
            it.toObject(model).also { model ->
                model.id = it.id
                model.createdAt = it.data["createdAt"].toString().toLong()
                model.updatedAt = it.data["updatedAt"].toString().toLong()
            }
        }
    }

    fun getDocumentModel(snapshot : DocumentSnapshot, model: Class<T>) : T? {
        if (snapshot == null)
            return null
        else
            return snapshot.toObject(model).also { model ->
                model!!.id = snapshot.id
                model!!.createdAt = snapshot.data?.get("createdAt").toString().toLong()
                model!!.updatedAt = snapshot.data?.get("updatedAt").toString().toLong()
            }
    }
}