package android.afebrerp.com.gameover.presentation

import android.afebrerp.com.gameover.R
import android.afebrerp.com.gameover.presentation.navigator.NavigatorGameOverOut
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.game_over_activity.*
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class GameOverActivity : AppCompatActivity() , KoinComponent {

    private val navigatorGameOverOut : NavigatorGameOverOut by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_over_activity)
        buttonGameOn.setOnClickListener {
            navigatorGameOverOut.goToGameOnActivity(this)
        }
    }
}