package wahyouwebid.beritaq.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import wahyouwebid.beritaq.data.model.news.DataNews
import wahyouwebid.beritaq.data.state.NewsState
import wahyouwebid.beritaq.databinding.ActivitySearchBinding
import wahyouwebid.beritaq.ui.detail.DetailActivity
import wahyouwebid.beritaq.ui.news.NewsAdapter

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {

    private val binding : ActivitySearchBinding by lazy {
        ActivitySearchBinding.inflate(layoutInflater)
    }

    private val adapter : NewsAdapter by lazy {
        NewsAdapter { item -> detailNews(item)}
    }

    private val viewModel : SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupToolbar()
        setupAdapter()
        setupViewModel()
    }

    private fun setupToolbar(){
        with(binding){
            imgBack.setOnClickListener{finish()}
        }
    }

    private fun setupAdapter(){
        with(binding){
            rvNews.also {
                it.adapter = adapter
                it.layoutManager = LinearLayoutManager(applicationContext)
            }
        }
    }

    private fun setupViewModel(){
        viewModel.state.observe(this@SearchActivity){
            when(it){
                is NewsState.Loading -> {
                    setupLoading(true)
                }
                is NewsState.Result -> {
                    successGetData(it.data.data)
                }
                is NewsState.Error -> {
                    setupError(it)
                }
            }
        }
        viewModel.data.observe(this@SearchActivity, adapter::submitList)
        viewModel.setupSearchNews(binding.etSearch, this)
    }

    private fun setupLoading(loading : Boolean){
        with(binding) {
            if (loading) {
                shNews.startShimmer()
                rvNews.visibility = View.INVISIBLE
                shNews.visibility = View.VISIBLE
                emptyLottie.visibility = View.GONE
            }else {
                shNews.stopShimmer()
                rvNews.visibility = View.VISIBLE
                shNews.visibility = View.INVISIBLE
                emptyLottie.visibility = View.GONE
            }
        }
    }

    private fun successGetData(data : List<DataNews>){
        with(binding){
            setupLoading(false)
            if(data.isEmpty()) {
                emptyLottie.visibility = View.VISIBLE
            } else {
                emptyLottie.visibility = View.GONE
            }
        }
    }


    private fun setupError(error : NewsState.Error){
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

    private fun detailNews(item: DataNews) {
        startActivity(Intent(this, DetailActivity::class.java).also {
            it.putExtra("data", item)
        })
    }

}