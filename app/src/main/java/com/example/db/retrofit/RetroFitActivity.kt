package com.example.db.retrofit

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.db.R
import kotlinx.android.synthetic.main.activity_retrofit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetroFitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ReposAdapter()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(GitHubService::class.java)

        val repos = service.listRepos("Casta626")

        repos.enqueue(object : Callback<List<Repo>>{
            override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
                call.cancel()
            }

            override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                if(response.isSuccessful) {
                    response.body()?.let { repos ->
                        (recyclerView.adapter as ReposAdapter).setNamesList(repos)
                    }
                } else {
                    Toast.makeText(this@RetroFitActivity, response.message(), Toast.LENGTH_LONG).show()
                }
            }

        })
    }
}