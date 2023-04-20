package dj2al.example.buddybuilder.ui.home.events

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import dj2al.example.buddybuilder.data.Resource
import dj2al.example.buddybuilder.data.home.EventsRepository
import dj2al.example.buddybuilder.data.home.UsersRepository
import dj2al.example.buddybuilder.data.models.Event
import dj2al.example.buddybuilder.data.models.Level
import dj2al.example.buddybuilder.data.models.toLevel
import dj2al.example.buddybuilder.data.utils.currentDateTime
import dj2al.example.buddybuilder.data.utils.withBase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EventsViewModel @Inject constructor(
    private val eventsRepository: EventsRepository,
    private val usersRepository: UsersRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val sportId = savedStateHandle.get<String>("sportId") ?: ""
    val sportName = savedStateHandle.get<String>("sportName") ?: ""
    val eventJson = savedStateHandle.get<String>("event") ?: ""

    private val _events = MutableStateFlow<Resource<List<Event>>?>(null)
    val events : StateFlow<Resource<List<Event>>?> = _events

    private val _event = MutableStateFlow(eventJson.let {
        if (it.isNotEmpty()) {
            Gson().fromJson(it, Event::class.java)
        } else {
            Event()
        }
    })
    val event : StateFlow<Event> = _event

    private val _manageEventResult = MutableStateFlow<Resource<Event>?>(null)
    val manageEventResult: StateFlow<Resource<Event>?> = _manageEventResult

    private val _areInputsValid = MutableStateFlow(false)
    val areInputsValid: StateFlow<Boolean> = _areInputsValid

    val startTime = MutableStateFlow<Long>(_event.value.startTime)
    val endTime = MutableStateFlow<Long>(_event.value.endTime)
    val minParticipants = MutableStateFlow<Int>(_event.value.minParticipants)
    val maxParticipants = MutableStateFlow<Int>(_event.value.maxParticipants)
    val level = MutableStateFlow<Level>(_event.value.level.toLevel())
    val court = MutableStateFlow<String>(_event.value.court)

    val isUpdating = MutableStateFlow(event.value.id.isNotEmpty())

    init {
        getEvents(sportId)
        validateInputs()
    }

    fun getEvents(sportId : String) = viewModelScope.launch {
        _events.value = Resource.Loading
        _events.value = eventsRepository.getSportEvents(sportId)
    }

    fun validateInputs() {
        _areInputsValid.value = startTime.value > currentDateTime &&
                endTime.value > startTime.value &&
                minParticipants.value  >= 0 &&
                maxParticipants.value > minParticipants.value &&
                court.value != ""
        /* TODO: will need more validation */
    }

    fun createOrUpdateEvent() = viewModelScope.launch {
        _manageEventResult.value = Resource.Loading
        if (_event.value.id.isEmpty()) {
            _event.value = Event(
                sportName= sportName,
                sport = sportId,
                startTime = startTime.value,
                endTime = endTime.value,
                minParticipants = minParticipants.value,
                maxParticipants = maxParticipants.value,
                nbParticipants = 1,
                level = level.value.value,
                court = court.value,
                responsable = usersRepository.getUser().let { user ->
                    when (user) {
                        is Resource.Success -> user.result.id
                        else -> ""
                    }
                }
            )
            _manageEventResult.value = eventsRepository.addEvent(_event.value)
        } else {
            _event.value = _event.value.copy(
                startTime = startTime.value,
                endTime = endTime.value,
                minParticipants = minParticipants.value,
                maxParticipants = maxParticipants.value,
                level = level.value.value,
                court = court.value,
            ).withBase(_event.value)
            _manageEventResult.value = eventsRepository.updateEvent(_event.value)
        }
        resetResource()
        getEvents(sportId)
    }

    fun addEventToUser(event: Event) = viewModelScope.launch {
        usersRepository.addEventToUser(event.id)
    }

    fun deleteEvent() = viewModelScope.launch {
        eventsRepository.deleteEvent(_event.value.id)
    }

    fun resetResource() {
        isUpdating.value = false
        _event.value = Event()
    }
}