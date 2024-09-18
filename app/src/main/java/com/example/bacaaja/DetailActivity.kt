package com.example.bacaaja

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ARTICLE = "extra_article"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)

        val tvObject: TextView = findViewById(R.id.tv_title)

        val article = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Article>(EXTRA_ARTICLE, Article::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Article>(EXTRA_ARTICLE)
        }

        if (article != null) {
            tvObject.text = article.title.toString()
        }
    }
}