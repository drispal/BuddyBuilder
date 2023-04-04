package dj2al.example.buddybuilder.ui.home.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dj2al.example.buddybuilder.data.Resource
import dj2al.example.buddybuilder.data.home.EventsRepository
import dj2al.example.buddybuilder.data.home.SportsRepository
import dj2al.example.buddybuilder.data.home.UsersRepository
import dj2al.example.buddybuilder.data.models.Event
import dj2al.example.buddybuilder.data.models.Sport
import dj2al.example.buddybuilder.data.models.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val eventsRepository: EventsRepository,
    private val usersRepository: UsersRepository
) : ViewModel() {

    private val _events = MutableStateFlow<Resource<List<Event>>?>(null)
    val events: StateFlow<Resource<List<Event>>?> = _events

    private val _myEvents = MutableStateFlow<Resource<List<Event>>?>(null)
    val myEvents: StateFlow<Resource<List<Event>>?> = _myEvents

    private val _user = MutableStateFlow<Resource<User>?>(null)
    val user: StateFlow<Resource<User>?> = _user
    
    init {
        getUser()
        getEvents()
        getMyEvents()
    }

    private fun getUser() = viewModelScope.launch {
        _user.value = Resource.Loading
        _user.value = usersRepository.getUser()
    }

    private fun getMyEvents() = viewModelScope.launch {
        _myEvents.value = Resource.Loading
        _myEvents.value = eventsRepository.getUserEvents()
    }

    private fun getEvents() = viewModelScope.launch {
        _events.value = Resource.Loading
        _events.value = eventsRepository.getAllEvents()
    }

}