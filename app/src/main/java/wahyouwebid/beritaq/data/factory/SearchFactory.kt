package wahyouwebid.beritaq.data.factory

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import wahyouwebid.beritaq.data.model.news.DataNews
import wahyouwebid.beritaq.data.source.SearchSource
import wahyouwebid.beritaq.data.state.NewsState
import javax.inject.Inject

/**
 * Created by : wahyouwebid.
 * Email : hello@wahyou.web.id.
 * Linkedin : linkedin.com/in/wahyouwebid.
 * Instagram : instagram.com/wahyouwebid.
 * Portopolio : wahyou.web.id.
 */

class SearchFactory @Inject constructor(
    private val source: SearchSource
) : DataSource.Factory<Int, DataNews>() {

    lateinit var liveData : MutableLiveData<NewsState>
    var query : String = ""

    override fun create(): DataSource<Int, DataNews> {
        return source.also {
            it.query = query
            it.liveData = liveData
        }
    }
}