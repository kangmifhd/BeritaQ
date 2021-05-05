package wahyouwebid.beritaq.ui.news

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import dagger.hilt.android.lifecycle.HiltViewModel
import wahyouwebid.beritaq.data.model.news.DataNews
import wahyouwebid.beritaq.data.repository.Repository
import wahyouwebid.beritaq.data.state.NewsState
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    val state : MutableLiveData<NewsState> by lazy {
        MutableLiveData()
    }

    val data : MutableLiveData<PagedList<DataNews>> by lazy {
        MutableLiveData()
    }

    fun getNews(category : String, lifecycleOwner: LifecycleOwner){
        repository.getNews(category, state, data, lifecycleOwner)
    }
}