package dj2al.example.buddybuilder.data.home

import android.content.Context
import androidx.compose.ui.res.stringResource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import dagger.hilt.android.qualifiers.ApplicationContext
import dj2al.example.buddybuilder.R
import dj2al.example.buddybuilder.data.BaseRepository
import dj2al.example.buddybuilder.data.Resource
import dj2al.example.buddybuilder.data.models.Sport
import dj2al.example.buddybuilder.data.models.User
import dj2al.example.buddybuilder.data.utils.await
import dj2al.example.buddybuilder.data.utils.currentDateTime
import javax.inject.Inject



class SportsRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    auth: FirebaseAuth,
    firestore: FirebaseFirestore,
    private val usersRepository: UsersRepository
): SportsRepository, BaseRepository<Sport>(auth) {

    protected val db = firestore.collection("sports")

    override suspend fun getSports(): Resource<List<Sport>> {
        return try {
            val snapshot = db.get().await()
            Resource.Success(getData(snapshot, Sport::class.java))
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }

    override suspend fun getMySports(): Resource<List<Sport>> {
        return try {
            val snapshot = db.get().await()
            val sports = getData(snapshot, Sport::class.java)
            usersRepository.getUser().let { user ->
                if (user is Resource.Success) {
                    val mySports = sports.filter { sport ->
                        user.result.subscribedSports.contains(sport.id)
                    }
                    return Resource.Success(mySports)
                } else {
                    throw Exception("Can't get user info's")
                }
            }
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }

    override suspend fun addSport(sport: Sport): Resource<List<Sport>> {
        return try {
            db.add(sport)
            getSports()
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }

    override suspend fun updateSport(sport: Sport): Resource<Sport> {
        return try {
            db.document(sport.id).set(sport)
            val snapshot = db.document(sport.id).get().await()
            Resource.Success(getDocumentModel(snapshot, Sport::class.java) as Sport)
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }

    override suspend fun deleteSport(id: String): Resource<Boolean> {
        return try {
            db.document(id).delete()
            Resource.Success(true)
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }
}


