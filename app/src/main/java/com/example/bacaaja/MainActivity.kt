package com.example.bacaaja

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bacaaja.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val list = ArrayList<Article>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvArticles.setHasFixedSize(true)

        list.addAll(getListArticles())
        showRecyclerList()
    }

    private fun getListArticles(): ArrayList<Article> {
        val dataTitle = resources.getStringArray(R.array.data_title)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val dataContent = resources.getStringArray(R.array.data_content)
        val dataLike = resources.getIntArray(R.array.data_like)
        val dataBookmark = resources.getIntArray(R.array.data_bookmark)
        val dataReadTime = resources.getStringArray(R.array.data_read_time)
        val dataAuthorName = resources.getStringArray(R.array.data_author_name)
        val dataAuthorJob = resources.getStringArray(R.array.data_author_job)
        val dataAuthorPicture = resources.getStringArray(R.array.data_author_picture)
        val listHero = ArrayList<Article>()
        for (i in dataTitle.indices) {
            val hero = Article(dataTitle[i], dataDescription[i], dataPhoto[i], dataContent[i], dataLike[i], dataBookmark[i], dataReadTime[i],dataAuthorName[i], dataAuthorJob[i], dataAuthorPicture[i])
            listHero.add(hero)
        }
        return listHero
    }

    private fun showRecyclerList() {
        binding.rvArticles.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListArticleAdapter(list)
        binding.rvArticles.adapter = listHeroAdapter

        listHeroAdapter.setOnItemClickCallback(object : ListArticleAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Article) {
                val detailActivity = Intent(this@MainActivity, DetailActivity::class.java)
                detailActivity.putExtra(DetailActivity.EXTRA_ARTICLE, data)
                startActivity(detailActivity)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> {
                val userActivity = Intent(this@MainActivity, UserActivity::class.java)
                startActivity(userActivity)
            }
            android.R.id.home -> {
                this.finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}