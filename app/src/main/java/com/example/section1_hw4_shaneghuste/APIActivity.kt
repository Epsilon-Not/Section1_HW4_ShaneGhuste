package com.example.section1_hw4_shaneghuste

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.section1_hw4_shaneghuste.api.CatJson
import kotlinx.android.synthetic.main.activity_api.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

const val BASE_URL = "https://cat-fact.herokuapp.com"

class APIActivity : AppCompatActivity() {

    private  var TAG = "APIActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api)


        val btnBack = findViewById<Button>(R.id.apiBtnBack)
        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        getCurrentData()

        generateNew.setOnClickListener {
            getCurrentData()
        }
    }

    private fun getCurrentData(){

        catFact.visibility = View.INVISIBLE
        timeStamp.visibility = View.INVISIBLE
        progressBar3.visibility = View.VISIBLE

        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIRequest::class.java)
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response: Response<CatJson> = api.getCatFacts().awaitResponse()
                if (response.isSuccessful) {
                    val data: CatJson = response.body()!!
                    Log.d(TAG, data.text)

                    withContext(Dispatchers.Main) {
                        catFact.visibility = View.VISIBLE
                        timeStamp.visibility = View.VISIBLE
                        progressBar3.visibility = View.GONE

                        catFact.text = data.text
                        timeStamp.text = data.createdAt


                    }
                }
            } catch (e: Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(applicationContext, "You Need InterWebs For CatFacts", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}