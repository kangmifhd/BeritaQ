package wahyouwebid.beritaq.data.repository

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import io.reactivex.disposables.CompositeDisposable
import wahyouwebid.beritaq.data.model.categories.Categories
import wahyouwebid.beritaq.data.model.news.DataNews
import wahyouwebid.beritaq.data.state.NewsState
import wahyouwebid.beritaq.data.state.SourceState

/**
 * Created by : wahyouwebid.
 * Email : hello@wahyou.web.id.
 * Linkedin : linkedin.com/in/wahyouwebid.
 * Instagram : instagram.com/wahyouwebid.
 * Portopolio : wahyou.web.id.
 */

interface Repository {

    fun getHeadlines(
        callback: MutableLiveData<NewsState>
    )

    fun getSources(
        categories: String,
        callback : MutableLiveData<SourceState>
    )

    fun getNews(
        sources : String,
        callback: MutableLiveData<NewsState>,
        data: MutableLiveData<PagedList<DataNews>>,
        lifecycleOwner: LifecycleOwner
    )

    fun searchNews(
        query : String,
        callback: MutableLiveData<NewsState>,
        data: MutableLiveData<PagedList<DataNews>>,
        lifecycleOwner: LifecycleOwner
    )

    fun getCategories(context : Context) : List<Categories>

    fun getDisposable() : CompositeDisposable
}