package wahyouwebid.beritaq.data.factory

import javax.inject.Inject

/**
 * Created by : wahyouwebid.
 * Email : hello@wahyou.web.id.
 * Linkedin : linkedin.com/in/wahyouwebid.
 * Instagram : instagram.com/wahyouwebid.
 * Portopolio : wahyou.web.id.
 */

data class Factory @Inject constructor(
    val newsFactory : NewsFactory,
    val searchFactory : SearchFactory
)
