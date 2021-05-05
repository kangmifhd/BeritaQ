package wahyouwebid.beritaq.data.model.news

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Created by : wahyouwebid.
 * Email : hello@wahyou.web.id.
 * Linkedin : linkedin.com/in/wahyouwebid.
 * Instagram : instagram.com/wahyouwebid.
 * Portopolio : wahyou.web.id.
 */

@Parcelize
data class Source(
    @SerializedName("id")
    val id: String? = "",

    @SerializedName("name")
    val name: String? = ""
) : Parcelable

