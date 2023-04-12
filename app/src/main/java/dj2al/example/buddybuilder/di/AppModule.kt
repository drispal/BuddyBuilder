package dj2al.example.buddybuilder.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dj2al.example.buddybuilder.data.auth.AuthRepository
import dj2al.example.buddybuilder.data.auth.AuthRepositoryImpl
import dj2al.example.buddybuilder.data.home.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    @Provides
    fun provideFirebaseDb(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    fun provideSportsRepository(impl : SportsRepositoryImpl): SportsRepository = impl

    @Provides
    fun provideUsersRepository(impl : UsersRepositoryImpl): UsersRepository = impl

    @Provides
    @Singleton //Remove when using real repository
    fun provideEventsRepository(impl : EventsRepositoryImplFake): EventsRepository = impl


}