package wahyouwebid.beritaq.data.state

import wahyouwebid.beritaq.data.model.source.ResponseSource

/**
 * Created by : wahyouwebid.
 * Email : hello@wahyou.web.id.
 * Linkedin : linkedin.com/in/wahyouwebid.
 * Instagram : instagram.com/wahyouwebid.
 * Portopolio : wahyou.web.id.
 */

sealed class SourceState {
    object Loading : SourceState()
    data class Result(val data : ResponseSource) : SourceState()
    data class Error(val error : Throwable) : SourceState()
}
