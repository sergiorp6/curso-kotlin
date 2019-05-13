package es.pue.github.ui.activities

import android.content.Context
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager

import es.pue.github.R
import es.pue.github.api.RepositoryEngine
import es.pue.github.data.RepoResult
import es.pue.github.ui.adapters.RepoListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val engine = RepositoryEngine()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        repolist.layoutManager = LinearLayoutManager(this)
        val callback = object : Callback<RepoResult> {
            override fun onFailure(call: Call<RepoResult>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<RepoResult>, response: Response<RepoResult>) {
                response.isSuccessful.let {
                    val resultList = RepoResult(response.body()?.items ?: emptyList())
                    repolist.adapter = RepoListAdapter(resultList)
                }
            }
        }

        if (isNetworkConnected()) {
            engine.getRepositories(callback)
        } else {
            AlertDialog
                .Builder(this)
                .setTitle("Sin conectividad")
                .setMessage("Verifique la conexión a Internet")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.ok) {_ , _ -> }
                .show()
        }

    }

    fun isNetworkConnected(): Boolean {
        val connectManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo =  connectManager.activeNetworkInfo

        return networkInfo != null && networkInfo.isConnected
    }
}
