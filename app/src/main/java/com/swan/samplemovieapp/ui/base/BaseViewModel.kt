package com.swan.samplemovieapp.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swan.samplemovieapp.data.models.response.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    protected open fun <T> fetchListData(
        mutableLiveData: MutableLiveData<Resource<List<T>>>,
        fetcher: suspend () -> List<T>,
    ) {
        fetchData(mutableLiveData, fetcher)
    }

    protected open fun <T> fetchData(
        mutableLiveData: MutableLiveData<Resource<T>>,
        fetcher: suspend () -> T,
    ) {
        mutableLiveData.postValue(Resource.Loading(mutableLiveData.value?.data))

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val apiResponse = fetcher()

                mutableLiveData.postValue(
                    Resource.Success(apiResponse)
                )
            } catch (e: Throwable) {
                e.printStackTrace()

                mutableLiveData.postValue(
                    Resource.Error(e)
                )
            }
        }
    }
}