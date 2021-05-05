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
data class DataNews(
    @SerializedName("source")
    var modelSource: Source?,

    @SerializedName("author")
    var author: String? = "",

    @SerializedName("title")
    var title: String? = "",

    @SerializedName("description")
    var description: String? = "",

    @SerializedName("url")
    var url: String? = "",

    @SerializedName("urlToImage")
    var urlToImage: String? = "",

    @SerializedName("publishedAt")
    var publishedAt: String? = "",

    @SerializedName("content")
    var content: String? = ""

) : Parcelable
