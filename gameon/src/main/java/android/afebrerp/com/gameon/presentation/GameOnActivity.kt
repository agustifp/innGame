package android.afebrerp.com.gameon.presentation

import android.afebrerp.com.gameon.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class GameOnActivity : AppCompatActivity(),KoinComponent {

    private val navigatorGameOverOut : NavigatorGameOnOut by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_on)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        navigatorGameOverOut.goToGameOverActivity(this)
    }
}
