package wahyouwebid.beritaq.data.model.news

import com.google.gson.annotations.SerializedName

/**
 * Created by : wahyouwebid.
 * Email : hello@wahyou.web.id.
 * Linkedin : linkedin.com/in/wahyouwebid.
 * Instagram : instagram.com/wahyouwebid.
 * Portopolio : wahyou.web.id.
 */

data class ResponseNews(
        @SerializedName("status")
        val status: String = "",

        @SerializedName("totalResults")
        val totalResults: Int = 0,

        @SerializedName("articles")
        val data: List<DataNews> = emptyList()
)
