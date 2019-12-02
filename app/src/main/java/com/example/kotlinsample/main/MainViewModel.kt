package com.example.kotlinsample.main

import androidx.lifecycle.MutableLiveData
import com.example.kotlinsample.base.BaseViewModel
import com.example.kotlinsample.model.UserDetail
import com.example.kotlinsample.model.UserInfo
import com.example.kotlinsample.repository.UserRepository
import kotlinx.coroutines.launch

class MainViewModel : BaseViewModel() {

    val userInfo = MutableLiveData<MutableList<UserInfo>>()
    val moreUserInfo = MutableLiveData<MutableList<UserInfo>>()
    val userDetail = MutableLiveData<UserDetail>()

    fun loadUserList(id : String) {
        ioScope.launch {
            UserRepository.getUserLists(id).let {
                userInfo.postValue(it.body())
            }
        }
    }

    fun loadMoreUserList(id : String) {
        ioScope.launch {
            UserRepository.getUserLists(id).let {
                moreUserInfo.postValue(it.body())
            }
        }
    }

    fun loadUserDetail(name : String) {
        ioScope.launch {
            UserRepository.getUserDetail(name).let {
                userDetail.postValue(it)
            }
        }
    }

}