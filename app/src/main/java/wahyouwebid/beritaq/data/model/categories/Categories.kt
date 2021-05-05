package wahyouwebid.beritaq.data.model.categories

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by : wahyouwebid.
 * Email : hello@wahyou.web.id.
 * Linkedin : linkedin.com/in/wahyouwebid.
 * Instagram : instagram.com/wahyouwebid.
 * Portopolio : wahyou.web.id.
 */

@Parcelize
data class Categories(
        var id: String?,
        var title: String?,
        var icon: String?,
        var color: String?
) : Parcelable
