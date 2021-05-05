package wahyouwebid.beritaq.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import wahyouwebid.beritaq.data.model.categories.Categories
import wahyouwebid.beritaq.data.model.news.DataNews
import wahyouwebid.beritaq.data.state.NewsState
import wahyouwebid.beritaq.databinding.ActivityMainBinding
import wahyouwebid.beritaq.ui.detail.DetailActivity
import wahyouwebid.beritaq.ui.main.adapter.CategoriesAdapter
import wahyouwebid.beritaq.ui.main.adapter.HeadlinesAdapter
import wahyouwebid.beritaq.ui.search.SearchActivity
import wahyouwebid.beritaq.ui.source.SourceActivity


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val adapterCategories : CategoriesAdapter by lazy {
        CategoriesAdapter{ item -> detailCategories(item)}
    }

    private val adapterHeadlines : HeadlinesAdapter by lazy {
        HeadlinesAdapter { item -> detailNews(item)}
    }

    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupAdapter()
        setupViewModel()
        setupListener()
    }

    private fun setupAdapter(){
        with(binding){
            rvCategories.also {
                it.adapter = adapterCategories
                it.layoutManager = GridLayoutManager(applicationContext, 2)
            }

            rvHeadlines.also {
                it.adapter = adapterHeadlines
                it.layoutManager = LinearLayoutManager(applicationContext)
            }
        }
    }

    private fun setupViewModel(){
        viewModel.state.observe(this){
            when(it){
                is NewsState.Loading -> {
                    setupLoading(true)
                }
                is NewsState.Result -> {
                    setupLoading(false)
                    adapterHeadlines.setData(it.data.data)
                }
                is NewsState.Error -> {
                    setupError(it)
                }
            }
        }
        viewModel.getHeadlines()

        val data = viewModel.getCategories(applicationContext)
        adapterCategories.setData(data)
    }

    private fun setupLoading(loading : Boolean){
        with(binding) {
            if (loading) {
                rvHeadlines.visibility = View.INVISIBLE
                shHeadlines.visibility = View.VISIBLE
                shHeadlines.startShimmer()
            }else {
                rvHeadlines.visibility = View.VISIBLE
                shHeadlines.visibility = View.INVISIBLE
                shHeadlines.stopShimmer()
            }
        }
    }


    private fun setupError(error : NewsState.Error){
        setupLoading(false)
        if (error.error.message != null) Toast.makeText(
                applicationContext,
                error.error.message!!,
                Toast.LENGTH_SHORT
        ).show()
    }

    private fun setupListener(){
        with(binding){
            search.setOnClickListener {
                startActivity(Intent(applicationContext, SearchActivity::class.java))
            }
        }
    }

    private fun detailCategories(item: Categories) {
        startActivity(Intent(this, SourceActivity::class.java).also {
            it.putExtra("data", item)
        })
    }

    private fun detailNews(item: DataNews) {
        startActivity(Intent(this, DetailActivity::class.java).also {
            it.putExtra("data", item)
        })
    }

}