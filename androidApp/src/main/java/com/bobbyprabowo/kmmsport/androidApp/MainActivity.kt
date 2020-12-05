package com.bobbyprabowo.kmmsport.androidApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bobbyprabowo.kmmsport.shared.Greeting
import android.widget.TextView
import android.widget.Toast
import com.badoo.reaktive.scheduler.Scheduler
import com.badoo.reaktive.scheduler.ioScheduler
import com.badoo.reaktive.scheduler.mainScheduler
import com.badoo.reaktive.single.observeOn
import com.badoo.reaktive.single.subscribe
import com.badoo.reaktive.single.subscribeOn
import com.badoo.reaktive.single.threadLocal
import com.bobbyprabowo.kmmsport.shared.datasource.SportsAPI
import com.bobbyprabowo.kmmsport.shared.datasource.impl.SportsAPIImpl
import kotlin.coroutines.CoroutineContext

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)
        tv.text = greet()
        val sportsAPI = SportsAPIImpl()
        sportsAPI.searchTeam("arsenal")
            .subscribeOn(ioScheduler)
            .observeOn(mainScheduler)
            .threadLocal()
            .subscribe {
                Toast.makeText(this, it.toString(), Toast.LENGTH_LONG).show()
            }
    }
}
