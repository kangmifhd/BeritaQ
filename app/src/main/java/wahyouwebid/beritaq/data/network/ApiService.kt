package wahyouwebid.beritaq.data.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import wahyouwebid.beritaq.data.model.news.ResponseNews
import wahyouwebid.beritaq.data.model.source.ResponseSource

/**
 * Created by : wahyouwebid.
 * Email : hello@wahyou.web.id.
 * Linkedin : linkedin.com/in/wahyouwebid.
 * Instagram : instagram.com/wahyouwebid.
 * Portopolio : wahyou.web.id.
 */

interface ApiService {

    @GET("top-headlines?country=us")
    fun getHeadline(): Single<ResponseNews>

    @GET("sources")
    fun getSource(
            @Query("category") category: String?
    ): Single<ResponseSource>

    @GET("top-headlines")
    fun getNews(
        @Query("sources") sources: String?,
        @Query("page") page: Int,
    ): Single<ResponseNews>

    @GET("everything")
    fun searchNews(
        @Query("q") keyword: String?,
        @Query("page") page: Int,
    ): Single<ResponseNews>
}