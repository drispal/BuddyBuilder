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
    private val usersRepository: UsersRepository,
    private val sportsRepository: SportsRepository,
    private val eventsRepository: EventsRepository
) : ViewModel() {
    
    private val _sports = MutableStateFlow<Resource<List<Sport>>?>(null)
    val sports: StateFlow<Resource<List<Sport>>?> = _sports

    private val _events = MutableStateFlow<Resource<List<Event>>?>(null)
    val events: StateFlow<Resource<List<Event>>?> = _events
    
    init {
        getUserSports()
        getUserEvents()
    }
    
    private fun getUserSports() {
        // TODO: Implement this function 
    }

    private fun getUserEvents() {
        // TODO: Implement this function
    }

}