package wahyouwebid.beritaq.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import wahyouwebid.beritaq.data.repository.DataRepository
import wahyouwebid.beritaq.data.repository.Repository

@Module
@InstallIn(SingletonComponent::class)
abstract class BindingModule {
    @Binds
    abstract fun bindingRepository(
        dataRepository: DataRepository
    ) : Repository
}