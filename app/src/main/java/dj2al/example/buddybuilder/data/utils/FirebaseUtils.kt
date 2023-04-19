package dj2al.example.buddybuilder.data.utils

import com.google.android.gms.tasks.Task
import dj2al.example.buddybuilder.data.models.BaseModel
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resumeWithException

suspend fun <T> Task<T>.await(): T {
    return suspendCancellableCoroutine { cont ->
        addOnCompleteListener {
            if (it.exception != null) {
                cont.resumeWithException(it.exception!!)
            } else {
                cont.resume(it.result, null)
            }
        }
    }
}

fun <T: BaseModel> T.withBase(base: BaseModel): T {
    id = base.id
    createdAt = base.createdAt
    updatedAt = base.updatedAt
    return this
}

