package wahyouwebid.beritaq.data.state

import wahyouwebid.beritaq.data.model.news.ResponseNews

/**
 * Created by : wahyouwebid.
 * Email : hello@wahyou.web.id.
 * Linkedin : linkedin.com/in/wahyouwebid.
 * Instagram : instagram.com/wahyouwebid.
 * Portopolio : wahyou.web.id.
 */

sealed class NewsState {
    object Loading : NewsState()
    data class Result(val data : ResponseNews) : NewsState()
    data class Error(val error : Throwable) : NewsState()
}
