package es.pue.github.api

import es.pue.github.data.RepoResult
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepositoryEngine {

    private val service: GithubService
    companion object {
        const val BASE_URL = "https://api.github.com/"
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(GithubService::class.java)
    }

    fun getRepositories(callback: Callback<RepoResult>) {
        service.getRepositories().enqueue(callback)
    }
}