package es.pue.github.data

import com.google.gson.Gson

class Request {

    companion object {
        private val URL  = "https://api.github.com/repositories"
        private val SEARCH = "q=language:kotlin&sort:stars&order=desc"
        private val COMPLETE_URL = "$URL?$SEARCH"
    }

    fun run(): RepoResult {
        val repoListJsonStr = java.net.URL(COMPLETE_URL).readText()

        return Gson().fromJson(repoListJsonStr, RepoResult::class.java)
    }
}