package com.example.kotlinsample.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlinsample.R
import com.example.kotlinsample.model.UserInfo
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.example.kotlinsample.main.MainActivity.Companion.mTotalCount


class UserListAdapter (private val context: Context, private var mListener : OnItemClickListener) : RecyclerView.Adapter<UserListAdapter.ViewHolder>(){

    var mUserlists: MutableList<UserInfo> = mutableListOf()

    interface OnItemClickListener {
        fun onItemClick(item : UserInfo)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, index: Int): ViewHolder {
        val rootView = LayoutInflater.from(viewGroup.context).inflate(R.layout.user_list_item, viewGroup, false)
        return ViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return mUserlists.size
    }

    fun refreshData(userlists: MutableList<UserInfo>) {
        if (userlists.size > 20) {
            mUserlists = userlists.subList(0, 20)
        } else {
            mUserlists = userlists
        }
        mTotalCount = mUserlists.size
        notifyDataSetChanged()
    }

    fun addData(userlists: MutableList<UserInfo>) {
        if (userlists.size > 20) {
            for (i in userlists.subList(0, 20)) {
                mUserlists.add(i)
            }
        } else {
            for (i in userlists) {
                mUserlists.add(i)
            }
        }
        mTotalCount = mUserlists.size
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, index: Int) {

        viewHolder.name.text = mUserlists[index].login
        if (mUserlists[index].site_admin!!) {
            viewHolder.site_admin.visibility = View.VISIBLE
        } else {
            viewHolder.site_admin.visibility = View.GONE
        }

        Glide.with(context)
            .load(mUserlists[index].avatar_url)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .into(viewHolder.user_img)

        viewHolder.itemView.setOnClickListener {
            mListener.onItemClick(mUserlists[index])
        }
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        var name: TextView = itemView.findViewById(R.id.user_name) as TextView
        var site_admin: TextView = itemView.findViewById(R.id.user_site_admin) as TextView
        var user_img: ImageView = itemView.findViewById(R.id.user_img) as ImageView
    }
}