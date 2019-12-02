package com.example.kotlinsample.main

import android.content.Intent
import android.net.Uri
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.example.kotlinsample.R
import com.example.kotlinsample.base.BaseActivity
import com.example.kotlinsample.model.UserDetail
import kotlinx.android.synthetic.main.user_detail_layout.*
import org.parceler.Parcels

class UserDetailActivity : BaseActivity(){

    companion object {
        val BUNDLE_USER_DETAIL = "BUNDLE_USER_DETAIL"
    }

    lateinit var mUserDetail: UserDetail


    override fun getLayoutResId(): Int {
        return R.layout.user_detail_layout
    }

    override fun initView() {
        mUserDetail = Parcels.unwrap<UserDetail>(intent.extras!!.getParcelable(BUNDLE_USER_DETAIL))
        close_icon.setOnClickListener {
            this.finish()
        }
        if (mUserDetail.blog.isNullOrEmpty().not()) {
            user_blog.setOnClickListener {
                val openURL = Intent(Intent.ACTION_VIEW)
                openURL.data = Uri.parse(mUserDetail.blog)
                startActivity(openURL)
            }
        }

    }

    override fun initData() {
        mUserDetail.avatar_url.let {
            Glide.with(this)
                .load(mUserDetail.avatar_url)
                .apply(RequestOptions.bitmapTransform(CircleCrop()))
                .into(user_img)

        }

        user_name.text = mUserDetail.name

        if (mUserDetail.bio != null) {
            user_bio.text = mUserDetail.bio.toString()
        } else {
            user_bio.text = ""
        }

        user_login.text = mUserDetail.login
        if (mUserDetail.site_admin) {
            user_site_admin.visibility = View.VISIBLE
        } else {
            user_site_admin.visibility = View.GONE
        }
        mUserDetail.location?.let {
            user_location.text = mUserDetail.location
        }

        user_blog.text = mUserDetail.blog
    }
}