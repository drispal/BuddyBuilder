package dj2al.example.buddybuilder.ui.home.sports

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dj2al.example.buddybuilder.data.Resource
import dj2al.example.buddybuilder.data.home.SportsRepository
import dj2al.example.buddybuilder.data.home.UsersRepository
import dj2al.example.buddybuilder.data.models.Sport
import dj2al.example.buddybuilder.data.models.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SportsViewModel @Inject constructor(
    private val sportsRepository: SportsRepository,
    private val userRepository : UsersRepository
    ) : ViewModel() {

    private val _sports = MutableStateFlow<Resource<List<Sport>>?>(null)
    val sports: StateFlow<Resource<List<Sport>>?> = _sports

    private val _user = MutableStateFlow<Resource<User>?>(null)
    val user: StateFlow<Resource<User>?> = _user

    init {
        getSports()
        getUser()
    }

    private fun getSports() = viewModelScope.launch {
        _sports.value = Resource.Loading
        _sports.value = sportsRepository.getSports()
    }

    private fun getUser() = viewModelScope.launch {
        _user.value = Resource.Loading
        _user.value = userRepository.getUser()
    }

    fun addSportToUser(userId: String, sportId: String) = viewModelScope.launch {
        _user.value = userRepository.addSportToUser(userId, sportId)
    }

    fun addEventToUser(userId : String, eventId : String) = viewModelScope.launch {
        _user.value = userRepository.addEventToUser(userId, eventId)
    }

}