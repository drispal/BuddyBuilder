package dj2al.example.buddybuilder.ui.home.events

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dj2al.example.buddybuilder.data.Resource
import dj2al.example.buddybuilder.data.home.EventsRepository
import dj2al.example.buddybuilder.data.models.Event
import dj2al.example.buddybuilder.data.models.Level
import dj2al.example.buddybuilder.data.utils.currentDateTime
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(
    private val eventsRepository: EventsRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val sportId = savedStateHandle.get<String>("sportId") ?: ""
    val sportName = savedStateHandle.get<String>("sportName") ?: ""

    val startTime = MutableStateFlow<Long>(0)
    val endTime = MutableStateFlow<Long>(0)
    val minParticipants = MutableStateFlow<Int>(0)
    val maxParticipants = MutableStateFlow<Int>(0)
    val level = MutableStateFlow<Level>(Level.Level1)
    val court = MutableStateFlow<String>("")

    private val _areInputsValid = MutableStateFlow(false)
    val areInputsValid: StateFlow<Boolean> = _areInputsValid

    private val _manageEventResult = MutableStateFlow<Resource<Event>?>(null)
    val manageEventResult: StateFlow<Resource<Event>?> = _manageEventResult

    private val _events = MutableStateFlow<Resource<List<Event>>?>(null)
    val events : StateFlow<Resource<List<Event>>?> = _events

    init {
        getEvents(sportId)
    }

    fun validateInputs() {
        _areInputsValid.value = startTime.value > currentDateTime &&
                endTime.value > startTime.value &&
                minParticipants.value  >= 0 &&
                maxParticipants.value > minParticipants.value &&
                court.value != ""
        /* TODO: will need more validation */
    }

    fun addEvent() = viewModelScope.launch {
        _manageEventResult.value = Resource.Loading
        val event = Event(
            sport = sportId,
            startTime = startTime.value,
            endTime = endTime.value,
            minParticipants = minParticipants.value,
            maxParticipants = maxParticipants.value,
            level = level.value,
            court = court.value,
            responsable = "TODO"
        )
        _manageEventResult.value = eventsRepository.addEvent(event)
        getEvents(sportId)
    }

    fun resetResource() {
        _manageEventResult.value = null
    }

    private fun getEvents(sportId : String) = viewModelScope.launch {
        _events.value = Resource.Loading
        _events.value = eventsRepository.getSportEvents(sportId)
    }

}