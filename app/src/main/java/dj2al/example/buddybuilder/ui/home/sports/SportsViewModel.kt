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

    init {
        getSports()
        getMySports()
    }

    fun getSports() = viewModelScope.launch {
        _sports.value = Resource.Loading
        _sports.value = sportsRepository.getSports()
        println("ViewModel sports : ${_sports.value}")
    }

    fun getMySports() = viewModelScope.launch {
        _mysports.value = Resource.Loading
        _mysports.value = sportsRepository.getMySports()
        println("ViewModel my sports : ${_mysports.value}")
    }

    fun addSportToUser(sportId: String) = viewModelScope.launch {
        userRepository.addSportToUser(sportId)
        //println("ViewModel : ${_user.value}")
    }

    fun removeSportFromUser(sportId: String) = viewModelScope.launch {
        userRepository.removeSportFromUser(sportId)
        //println("ViewModel : ${_user.value}")
    }

}