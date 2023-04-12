package dj2al.example.buddybuilder.data.home

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dj2al.example.buddybuilder.data.BaseRepository
import dj2al.example.buddybuilder.data.Resource
import dj2al.example.buddybuilder.data.models.Sport
import dj2al.example.buddybuilder.data.models.User
import dj2al.example.buddybuilder.data.utils.await
import dj2al.example.buddybuilder.data.utils.currentDateTime
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    auth: FirebaseAuth,
    firestore: FirebaseFirestore
): UsersRepository, BaseRepository<User>(auth) {

    protected val db = firestore.collection("users")
    protected val user = db.document(auth.currentUser?.uid.toString())
    protected var model : User? = null

    override suspend fun getUser(): Resource<User> {
        return try {
            val snapshot = user.get().await()
            model = getDocumentModel(snapshot, User::class.java)
            if (model == null)
                Resource.Failure(Exception("User not found"))
            else
             Resource.Success(model as User)
        } catch (e: Exception) {
            println("UserRepository : $e")
            Resource.Failure(Exception("UserRepository : $e"))
        }
    }

    override suspend fun getAllUsers(): Resource<List<User>> {
        return try {
            val snapshot = db.get().await()
            Resource.Success(getData(snapshot, User::class.java))
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }

    override suspend fun addUser(id : String, u : User): Resource<List<User>> {
        return try {
            db.document(id).set(u)
            getAllUsers()
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }

    override suspend fun updateUser(u : User): Resource<User> {
        return try {
            db.document(u.id).set(u)
            getUser()
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }

    override suspend fun deleteUser(id : String): Resource<Boolean> {
        return try {
            db.document(id).delete()
            Resource.Success(true)
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }

    override suspend fun addEventToUser(eId: String): Resource<User> {
        try {
            getUser()
            model!!.subscribedEvents.add(eId)
            return updateUser(model!!)
        } catch (e: Exception) {
            return Resource.Failure(e)
        }
    }

    override suspend fun addSportToUser(sId: String): Resource<User> {
        try {
            getUser()
            model!!.subscribedSports.add(sId)
            return updateUser(model!!)
        } catch (e: Exception) {
            return Resource.Failure(e)
        }
    }

    override suspend fun removeSportFromUser(sId: String): Resource<User> {
        try {
            getUser()
            model!!.subscribedSports.remove(sId)
            return updateUser(model!!)
        } catch (e: Exception) {
            return Resource.Failure(e)
        }
    }

    override suspend fun removeEventFromUser(eId: String): Resource<User> {
        try {
            getUser()
            model!!.subscribedEvents.remove(eId)
            return updateUser(model!!)
        } catch (e: Exception) {
            return Resource.Failure(e)
        }
    }

}