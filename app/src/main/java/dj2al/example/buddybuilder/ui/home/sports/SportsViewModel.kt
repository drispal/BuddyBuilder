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

    private val _mysports = MutableStateFlow<Resource<List<Sport>>?>(null)
    val mysports: StateFlow<Resource<List<Sport>>?> = _mysports

    private val _user = MutableStateFlow<Resource<User>?>(null)
    val user: StateFlow<Resource<User>?> = _user

    init {
        getSports()
        getMySports()
    }

    private fun getSports() = viewModelScope.launch {
        _sports.value = Resource.Loading
        _sports.value = sportsRepository.getSports()
        println("ViewModel : ${_sports.value}")
    }

    private fun getMySports() = viewModelScope.launch {
        _mysports.value = Resource.Loading
        _mysports.value = sportsRepository.getMySports()
        println("ViewModel : ${_mysports.value}")
    }

    fun addSportToUser(sportId: String) = viewModelScope.launch {
        _user.value = userRepository.addSportToUser(sportId)
        getMySports()
        //println("ViewModel : ${_user.value}")
    }

    fun addEventToUser(eventId : String) = viewModelScope.launch {
        _user.value = userRepository.addEventToUser(eventId)
        getMySports()
        //println("ViewModel : ${_user.value}")
    }

    fun removeSportFromUser(sportId: String) = viewModelScope.launch {
        _user.value = userRepository.removeSportFromUser(sportId)
        getMySports()
        //println("ViewModel : ${_user.value}")
    }

    fun removeEventFromUser(eventId: String) = viewModelScope.launch {
        _user.value = userRepository.removeEventFromUser(eventId)
        getMySports()
        //println("ViewModel : ${_user.value}")
    }

}