package dj2al.example.buddybuilder.data.home

class AppRepositorys {
    val sportsRepository : SportsRepository
    val userRepository : UserRepository
    val eventRepository : EventRepository

    init {
        sportsRepository = SportsRepositoryImplFake()
        userRepository = UserRepositoryImplFake()
        eventRepository = EventRepositoryImplFake()
    }

}