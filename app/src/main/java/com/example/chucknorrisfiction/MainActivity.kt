package com.example.chucknorrisfiction

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Html.fromHtml

import android.view.View
import android.widget.Toast
import com.example.chucknorrisfiction.responce.Resp
import com.example.chucknorrisfiction.responce.Value
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {

    val url = "https://api.icndb.com/jokes/random"
    private var okHttpClient: OkHttpClient = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nextBtn.setOnClickListener {
            loadRandomFact()
        }
    }

    private fun loadRandomFact() {
        Toast.makeText(applicationContext, "Attempting to Fetch JSON",Toast.LENGTH_LONG).show()
        println("Attempting to Fetch JSON")
        runOnUiThread {
            progressBar.visibility = View.VISIBLE
        }

        val request: Request = Request.Builder().url(url).build()
        okHttpClient.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                Toast.makeText(applicationContext, "Error on Fetch JSON!!",Toast.LENGTH_LONG).show()
                println("Error on Fetch JSON!!")
            }

            override fun onResponse(call: Call?, response: Response?) {
                val json = response?.body()?.string()
                println(json)
                val gson = GsonBuilder().create()
                val homeFeed = gson.fromJson(json, Resp::class.java)
                val txt = (JSONObject(json).getJSONObject("value").get("joke")).toString()

                runOnUiThread {
                    progressBar.visibility = View.GONE
//                    factTv.text = fromHtml(txt)
                    factTv.text = fromHtml(homeFeed.value?.joke.toString())
                    println(factTv.text)
                }
            }
        })

    }
}

