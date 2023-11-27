package kr.ac.kumoh.ce.com.s20190839.s23w1301songlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SongViewModel() : ViewModel() {
    companion object {
        private val SERVER_URL = "https://port-0-app-programming-backend-1drvf2llomgoy08.sel5.cloudtype.app/"
    }


    private val _songList = MutableLiveData<List<Song>>()
    val songList: LiveData<List<Song>>
        get() = _songList

    private val songApi: SongApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        songApi = retrofit.create(SongApi::class.java)
        fetchSong()
    }

    private fun fetchSong() {
        viewModelScope.launch {
            try {
                val response = songApi.getSongs()
                _songList.value = response
            } catch (e: Exception) {
                Log.e("fetchSong()", e.toString())
            }
        }
    }
}