package wahyouwebid.beritaq.ui.source

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import wahyouwebid.beritaq.data.model.categories.Categories
import wahyouwebid.beritaq.data.model.source.DataSource
import wahyouwebid.beritaq.data.state.SourceState
import wahyouwebid.beritaq.databinding.ActivitySourceBinding
import wahyouwebid.beritaq.ui.news.NewsActivity

@AndroidEntryPoint
class SourceActivity : AppCompatActivity() {

    private val binding : ActivitySourceBinding by lazy {
        ActivitySourceBinding.inflate(layoutInflater)
    }

    private val adapter: SourceAdapter by lazy {
        SourceAdapter{ item -> detailSource(item)}
    }

    private val viewModel : SourceViewModel by viewModels()

    private val categories : Categories? by lazy {
        intent.getParcelableExtra("data")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupToolbar()
        setupAdapter()
        setupViewModel()
    }

    private fun setupToolbar(){
        with(binding){
            tvTitle.text = categories?.title
            imgBack.setOnClickListener{finish()}
        }
    }

    private fun setupAdapter(){
        with(binding){
            rvSource.also {
                it.adapter = adapter
                it.layoutManager = LinearLayoutManager(applicationContext)
                it.setHasFixedSize(true)
            }
        }
    }

    private fun setupViewModel(){
        viewModel.state.observe(this, {
            when(it){
                is SourceState.Loading   -> setupLoading(true)
                is SourceState.Result    -> successGetData(it.data.sources)
                is SourceState.Error     -> setupError(it)
            }
        })

        viewModel.getSource(categories?.id!!)
    }

    private fun setupError(error : SourceState.Error) {
        with(binding){
            setupLoading(false)
            if (error.error.message != null) Toast.makeText(
                    applicationContext,
                    error.error.message!!,
                    Toast.LENGTH_SHORT
            ).show()
            emptyLottie.visibility = View.VISIBLE
        }
    }

    private fun successGetData(data: List<DataSource>) {
        with(binding){
            setupLoading(false)
            if(data.isEmpty()) {
                emptyLottie.visibility = View.VISIBLE
            } else {
                adapter.setData(data)
                emptyLottie.visibility = View.GONE
            }
        }
    }

    private fun setupLoading(loading: Boolean) {
        with(binding) {
            if (loading) {
                shSource.startShimmer()
                rvSource.visibility = View.INVISIBLE
                shSource.visibility = View.VISIBLE
            }else {
                rvSource.visibility = View.VISIBLE
                shSource.visibility = View.INVISIBLE
                shSource.stopShimmer()
            }
        }
    }


    private fun detailSource(item: DataSource) {
        startActivity(Intent(this, NewsActivity::class.java).also {
            it.putExtra("data", item)
        })
    }

}