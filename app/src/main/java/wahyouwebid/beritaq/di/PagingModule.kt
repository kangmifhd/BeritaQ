package wahyouwebid.beritaq.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.disposables.CompositeDisposable
import wahyouwebid.beritaq.data.factory.Factory
import wahyouwebid.beritaq.data.factory.NewsFactory
import wahyouwebid.beritaq.data.factory.SearchFactory
import wahyouwebid.beritaq.data.network.ApiService
import wahyouwebid.beritaq.data.source.NewsSource
import wahyouwebid.beritaq.data.source.SearchSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PagingModule {
    @Provides
    @Singleton
    fun provideFactory(
        newsFactory: NewsFactory,
        searchFactory: SearchFactory
    ) : Factory = Factory(
            newsFactory,
            searchFactory
    )

    @Provides
    @Singleton
    fun provideNewsSource(
        apiService: ApiService,
        disposable: CompositeDisposable
    ) : NewsSource = NewsSource(apiService, disposable)

    @Provides
    @Singleton
    fun provideSearchSource(
        apiService: ApiService,
        disposable: CompositeDisposable
    ) : SearchSource = SearchSource(apiService, disposable)

    @Provides
    @Singleton
    fun provideNewsFactory(
        newsSource: NewsSource
    ) : NewsFactory = NewsFactory(newsSource)

    @Provides
    @Singleton
    fun provideSearchFactory(
        searchSource: SearchSource
    ) : SearchFactory = SearchFactory(searchSource)
}