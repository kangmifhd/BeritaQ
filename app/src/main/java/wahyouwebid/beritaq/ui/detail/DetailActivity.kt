package wahyouwebid.beritaq.ui.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import wahyouwebid.beritaq.data.model.news.DataNews
import wahyouwebid.beritaq.databinding.ActivityDetailBinding
import wahyouwebid.beritaq.utils.Utils

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private val binding : ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    private val data : DataNews? by lazy {
        intent.getParcelableExtra("data")
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding){
            if (data != null){
                if(data?.title != null){
                    tvTitle.text = data?.title
                }
                if (data?.content != null){
                    tvContent.text = Html.fromHtml(data?.content)
                }
                if(data?.publishedAt != null){
                    tvDateTime.text = Utils.dateFormat(data?.publishedAt)
                }
                if (data?.author != null && data?.modelSource?.name != null) {
                    tvNameSource.text = data?.author + " \u2022 " + data?.modelSource?.name
                } else {
                    tvNameSource.text = "-"
                }

                if(data?.urlToImage != null){
                    imgThumbnail.load(data?.urlToImage)
                }

                imgBack.setOnClickListener { finish() }
                imgShare.setOnClickListener { shareLink(data) }
                tvReadMore.setOnClickListener {
                    val bottomSheetFragment = WebViewFragment()
                    val bundle = Bundle()
                    bundle.putParcelable("data", data)
                    bottomSheetFragment.arguments = bundle
                    bottomSheetFragment.show(supportFragmentManager, "bottomSheetFragment")
                }
            }
        }
    }

    private fun shareLink(data: DataNews?){
        startActivity(Intent.createChooser(Intent(),"Share To:").also {
            it.action = Intent.ACTION_SEND
            it.putExtra(Intent.EXTRA_TEXT,data?.url)
            it.type="text/plain"
        })
    }
}