package com.example.ktorf

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.sql.ResultSet

class MainViewModel : ViewModel() {
    val stateData: MutableStateFlow<Result<Dogs>?> = MutableStateFlow(null)

    val client = HttpClient(CIO) {
        expectSuccess = false
        install(JsonFeature) {
            GsonSerializer()
        }
    }

    fun getDogs() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data: HttpResponse = client.get("https://api.thedogapi.com/v1/breeds")
                if (data.status.isSuccess()) {
                    stateData.value = Result.success(data.receive<Dogs>())
                } else {
                    stateData.value = Result.failure(Exception("Something failed"))
                }
            } catch (e: Exception) {
                stateData.value = Result.failure(e)
            }
        }
    }
}