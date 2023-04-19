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
        getEvents()
        getMyEvents()
        getUser()
    }

    fun getUser() = viewModelScope.launch {
        _user.value = Resource.Loading
        _user.value = usersRepository.getUser()
    }

    fun getMyEvents() = viewModelScope.launch {
        _myEvents.value = Resource.Loading
        _myEvents.value = eventsRepository.getUserEvents()
    }

    fun getEvents() = viewModelScope.launch {
        _events.value = Resource.Loading
        _events.value = eventsRepository.getAllEvents()
    }

    fun addEventToUser(event: Event) = viewModelScope.launch {
        if(usersRepository.isSubscribedToEvent(event.id)) return@launch
        if(event.nbParticipants >= event.maxParticipants) return@launch
        event.nbParticipants++
        eventsRepository.updateEvent(event)
        usersRepository.addEventToUser(event.id)
        getMyEvents()
    }

    fun removeEventFromUser(event: Event) = viewModelScope.launch {
        if(usersRepository.isResponsibleForEvent(event)) return@launch
        if(!usersRepository.isSubscribedToEvent(event.id)) return@launch
        if(event.nbParticipants <= 0) return@launch
        event.nbParticipants--
        eventsRepository.updateEvent(event)
        usersRepository.removeEventFromUser(event.id)
        getMyEvents()
    }

}