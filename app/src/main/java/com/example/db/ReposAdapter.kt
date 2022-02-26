package com.example.db

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.retrofit_list_item.view.*

class ReposAdapter : RecyclerView.Adapter<ReposAdapter.RepoViewHolder>() {
    private var repos: List<Repo> = emptyList()

    fun setNamesList(repos: List<Repo>){
        this.repos = repos
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.retrofit_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return repos.size
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val repo = repos[position]
        holder.itemView.idTextView.text = repo.id.toString()
        holder.itemView.nameTextView.text = repo.name
    }

    class RepoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}