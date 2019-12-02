package com.example.kotlinsample.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinsample.R
import com.example.kotlinsample.base.BaseActivity
import com.example.kotlinsample.model.UserInfo
import kotlinx.android.synthetic.main.activity_main.*
import org.parceler.Parcels


class MainActivity : BaseActivity() {

    companion object {
        var mTotalCount : Int = 0
    }
    lateinit var userinfoAdapter: UserListAdapter
    var mCanClick : Boolean= true
    var mCanLoad : Boolean= true

    override fun getLayoutResId() = R.layout.activity_main

    override fun initView() {
        observeViewModel()
        initRecyclerView()
    }

    override fun initData() {
        progressBar.visibility = View.VISIBLE
        viewModel.loadUserList("0")
    }

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    private fun initRecyclerView() {
        userinfoAdapter =
            UserListAdapter(this@MainActivity, object : UserListAdapter.OnItemClickListener {
                override fun onItemClick(item: UserInfo) {
                    if (mCanClick) {
                        progressBar.visibility = View.VISIBLE
                        viewModel.loadUserDetail(item.login)
                    }
                    mCanClick = false
                }
            })
        userlist_recyclerview.layoutManager = LinearLayoutManager(this@MainActivity)
        userlist_recyclerview.adapter = userinfoAdapter

        userlist_recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lastVisibleItemPosition: Int = (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                if (userinfoAdapter.mUserlists.size > 0
                    && mTotalCount < 100
                    && mCanLoad
                    && lastVisibleItemPosition > userinfoAdapter.mUserlists.size - 5) { //小於15就call

                    progressBar.visibility = View.VISIBLE
                    mCanLoad = false
                    viewModel.loadMoreUserList(userinfoAdapter.mUserlists[userinfoAdapter.mUserlists.size - 1].id.toString())
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

            }
        })
    }

    private fun observeViewModel() {
        viewModel.userInfo.observe(this, Observer {
            progressBar.visibility = View.GONE
            it?.let {
                userinfoAdapter.refreshData(it)
            }


        })

        viewModel.moreUserInfo.observe(this, Observer {
            progressBar.visibility = View.GONE
            mCanLoad = true
            if (it != null && it.size > 0) {
                userinfoAdapter.addData(it)
            } else {
                mCanLoad = false
            }
        })

        viewModel.userDetail.observe(this, Observer {
            progressBar.visibility = View.GONE
            it?.let {
                val bundle = Bundle()
                bundle.putParcelable(UserDetailActivity.BUNDLE_USER_DETAIL, Parcels.wrap(it))

                val intent = Intent()
                intent.setClass(this,UserDetailActivity::class.java)
                intent.putExtras(bundle)
                startActivity(intent)
                mCanClick = true
            }
        })
    }

}
