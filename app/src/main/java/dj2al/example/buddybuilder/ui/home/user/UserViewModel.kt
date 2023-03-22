package dj2al.example.buddybuilder.ui.home.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dj2al.example.buddybuilder.data.Resource
import dj2al.example.buddybuilder.data.home.UserRepository
import dj2al.example.buddybuilder.data.models.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    private val _user = MutableStateFlow<Resource<User>?>(null)
    val user: StateFlow<Resource<User>?> = _user

    init {
        getUser()
    }

    private fun getUser() = viewModelScope.launch {
        _user.value = Resource.Loading
        _user.value = repository.getUser()
    }
}