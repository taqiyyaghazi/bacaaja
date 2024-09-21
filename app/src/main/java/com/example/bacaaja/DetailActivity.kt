package com.example.bacaaja

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.bacaaja.databinding.ActivityDetailBinding
import com.example.bacaaja.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DetailActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityDetailBinding
    companion object {
        const val EXTRA_ARTICLE = "extra_article"
        const val EXTRA_SHARE = "extra_share"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true);

        val article = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_ARTICLE, Article::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_ARTICLE)
        }

        if (article != null) {
            Glide.with(binding.articlePhoto.context)
                .load(article.photo)
                .into(binding.articlePhoto)
            binding.articleTitle.text = article.title
            binding.articleDescription.text = article.description
            binding.articleContent.text = article.content
            findViewById<TextView>(R.id.article_like).text = article.like.toString()
            findViewById<TextView>(R.id.article_bookmark).text = article.bookmark.toString()
            findViewById<TextView>(R.id.article_read_time).text = article.readTime
            findViewById<TextView>(R.id.author_name).text = article.authorName
            findViewById<TextView>(R.id.author_job).text = article.authorJob
            val authorPicture = findViewById<ImageView>(R.id.author_picture)
            Glide.with(authorPicture.context)
                .load(article.authorPicture)
                .into(authorPicture)

        }

        binding.actionShare.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.action_share -> {
                val articleTitleText = binding.articleTitle.text.toString()

                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(EXTRA_SHARE, "Cek Artikel ini: $articleTitleText")
                startActivity(Intent.createChooser(shareIntent, "Bagikan melalui"))
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> {
                val userActivity = Intent(this@DetailActivity, UserActivity::class.java)
                startActivity(userActivity)
            }
            android.R.id.home -> {
                this.finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}