package wahyouwebid.beritaq.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.disposables.CompositeDisposable
import wahyouwebid.beritaq.data.factory.Factory
import wahyouwebid.beritaq.data.network.ApiService
import wahyouwebid.beritaq.data.repository.DataRepository
import wahyouwebid.beritaq.data.repository.remote.RemoteRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideRemoteRepository(
        disposable: CompositeDisposable,
        apiService: ApiService,
        factory: Factory
    ) : RemoteRepository = RemoteRepository(disposable, apiService, factory)

    @Provides
    @Singleton
    fun provideDataRepository(
        remoteRepository: RemoteRepository
    ) : DataRepository = DataRepository(remoteRepository)
}