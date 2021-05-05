package wahyouwebid.beritaq.data.repository

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import io.reactivex.disposables.CompositeDisposable
import wahyouwebid.beritaq.data.model.categories.Categories
import wahyouwebid.beritaq.data.model.news.DataNews
import wahyouwebid.beritaq.data.repository.remote.RemoteRepository
import wahyouwebid.beritaq.data.state.NewsState
import wahyouwebid.beritaq.data.state.SourceState
import javax.inject.Inject

/**
 * Created by : wahyouwebid.
 * Email : hello@wahyou.web.id.
 * Linkedin : linkedin.com/in/wahyouwebid.
 * Instagram : instagram.com/wahyouwebid.
 * Portopolio : wahyou.web.id.
 */

class DataRepository @Inject constructor(
    private val remoteRepository: RemoteRepository
) : Repository {

    override fun getCategories(context: Context): List<Categories> {
        return remoteRepository.getCategories(context)
    }

    override fun getHeadlines(callback: MutableLiveData<NewsState>) {
        remoteRepository.getHeadlines(callback)
    }

    override fun getSources(
            categories: String,
            callback: MutableLiveData<SourceState>
    ) {
        remoteRepository.getSources(categories, callback)
    }

    override fun getNews(
        sources: String,
        callback: MutableLiveData<NewsState>,
        data: MutableLiveData<PagedList<DataNews>>,
        lifecycleOwner: LifecycleOwner
    ) {
        remoteRepository.getNews(sources, callback, data, lifecycleOwner)
    }

    override fun searchNews(
            query: String,
            callback: MutableLiveData<NewsState>,
            data: MutableLiveData<PagedList<DataNews>>,
            lifecycleOwner: LifecycleOwner
    ) {
        remoteRepository.searchNews(query, callback, data, lifecycleOwner)
    }

    override fun getDisposable(): CompositeDisposable {
        return remoteRepository.getDisposable()
    }

}