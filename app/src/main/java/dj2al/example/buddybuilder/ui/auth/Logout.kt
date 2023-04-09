package dj2al.example.buddybuilder.ui.auth

import android.content.Context
import androidx.compose.runtime.Composable
import dj2al.example.buddybuilder.ui.commons.startNewActivity

@Composable
fun Logout(viewModel: AuthViewModel, context: Context) {
    viewModel.logout()
    context.startNewActivity(AuthActivity::class.java)
}