package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.myapplication.ModelClasses.Article
import com.example.myapplication.viewmodel.MainViewModel
import com.example.myapplication.viewmodel.MainViewModelFactory
import com.example.recyclerview.MyAdapter


lateinit var mainViewModel: MainViewModel
lateinit var adapter: MyAdapter
lateinit var recyclearview: RecyclerView
private var articles = mutableListOf<Article>()
var sensorManager: SensorManager? = null
var proximitysensor: Sensor? = null
var distance = 0
var linearLayoutManager: LinearLayoutManager? = null

class MainActivity : AppCompatActivity(), SensorEventListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideStatusBar()
        setContentView(R.layout.activity_main)

        //Retrofit Helper
      //  val newsService = NewsRetrofitHelper.getInstance().create(NewsService::class.java)

        val repository = (application as ApplicationClass).newsRepository

        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)


        recyclearview = findViewById<RecyclerView>(R.id.newsList)
         linearLayoutManager= LinearLayoutManager(this@MainActivity)
        recyclearview.layoutManager = linearLayoutManager
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclearview)

        sensorManager =  getSystemService(Context.SENSOR_SERVICE) as SensorManager
        proximitysensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        mainViewModel.newsarticles.observe(this, androidx.lifecycle.Observer {
            Log.d("newsIn", it.articles.toString())
            articles = it.articles.toList().toMutableList()
            adapter = MyAdapter(this@MainActivity, articles)
            recyclearview.adapter = adapter
           // startActivity(Intent(this,FullscreenActivity::class.java))


        })


    }
    private fun hideStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            window.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent)
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        distance = event!!.values[0].toInt()
        if (distance === 0) {
            val position2: Int = linearLayoutManager!!.findFirstCompletelyVisibleItemPosition()
            recyclearview.smoothScrollToPosition(position2 + 1)
        } else {
            return
        }    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {}

    override fun onPostResume() {
        super.onPostResume()
        sensorManager!!.registerListener(this, proximitysensor, SensorManager.SENSOR_DELAY_FASTEST)
    }

    override fun onPause() {
        super.onPause()
        sensorManager!!.unregisterListener(this)
    }


}

