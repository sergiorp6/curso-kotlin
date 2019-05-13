package es.pue.github.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import es.pue.github.R
import es.pue.github.data.Item
import es.pue.github.data.RepoResult
import kotlinx.android.synthetic.main.repo_item.view.*

class RepoListAdapter(private val repoList: RepoResult): RecyclerView.Adapter<RepoListAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.repo_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = repoList.items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindRepo(repoList.items[position])
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bindRepo(repo: Item) {
            with (repo) {
                itemView.username.text  = owner.login.orEmpty()
                itemView.reponame.text = full_name.orEmpty()
                itemView.repodescription.text = description.orEmpty()
                Picasso.get().load(owner.avatar_url).into(itemView.avatar)
            }
        }
    }
}