package wahyouwebid.beritaq.data.factory

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import wahyouwebid.beritaq.data.model.news.DataNews
import wahyouwebid.beritaq.data.source.NewsSource
import wahyouwebid.beritaq.data.state.NewsState
import javax.inject.Inject

/**
 * Created by : wahyouwebid.
 * Email : hello@wahyou.web.id.
 * Linkedin : linkedin.com/in/wahyouwebid.
 * Instagram : instagram.com/wahyouwebid.
 * Portopolio : wahyou.web.id.
 */

class NewsFactory @Inject constructor(
    private val source: NewsSource
) : DataSource.Factory<Int, DataNews>() {

    lateinit var liveData : MutableLiveData<NewsState>
    var sources : String = ""

    override fun create(): DataSource<Int, DataNews> {
        return source.also {
            it.sources = sources
            it.liveData = liveData
        }
    }
}