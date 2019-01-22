package android.afebrerp.com.inngame.presentation.gameover

import android.afebrerp.com.inngame.R
import android.afebrerp.com.inngame.presentation.main.MainPresenterImpl
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class GameOverActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_over_activity)
    }
}