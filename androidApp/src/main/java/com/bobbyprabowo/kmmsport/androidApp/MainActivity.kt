package com.bobbyprabowo.kmmsport.androidApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bobbyprabowo.kmmsport.shared.Greeting
import android.widget.TextView
import android.widget.Toast
import com.bobbyprabowo.kmmsport.shared.datasource.impl.SportsAPIImpl
import com.bobbyprabowo.kmmsport.shared.domain.impl.SearchTeamImpl
import com.bobbyprabowo.kmmsport.shared.repository.impl.SportsRepositoryImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.rx3.rxSingle

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)
        tv.text = greet()
    }

    override fun onStart() {
        super.onStart()

        val sportsAPI = SportsAPIImpl()
        val sportsRepository = SportsRepositoryImpl(sportsAPI)
        val searchTeam = SearchTeamImpl(sportsRepository)
        rxSingle {
            searchTeam.execute("arsenal")
        }
            .map { teamSearchResult ->
                teamSearchResult.teams.first()
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({ team ->
                Toast.makeText(this, team.strTeam, Toast.LENGTH_LONG).show()
            }, { error ->
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show()
            })
    }
}
