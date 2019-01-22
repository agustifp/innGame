package android.afebrerp.com.inngame.presentation.main

import android.afebrerp.com.inngame.R
import android.afebrerp.com.inngame.domain.model.entity.Hub
import android.afebrerp.com.inngame.domain.model.entity.Industry
import android.afebrerp.com.inngame.domain.model.entity.Player
import android.afebrerp.com.inngame.domain.model.entity.Resources
import android.afebrerp.com.inngame.presentation.gameover.GameOverActivity
import android.content.Intent
import android.os.Bundle

import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.hub_card_layout.*
import kotlinx.android.synthetic.main.industry_card_layout.*
import kotlinx.android.synthetic.main.resources_card_layout.*
import org.koin.standalone.KoinComponent

class MainActivity : AppCompatActivity(), KoinComponent, MainPresenter.View {
    private lateinit var mainPresenter: MainPresenter.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        mainPresenter = MainPresenterImpl(this)
        mainPresenter.paintInitialState()
        fab.setOnClickListener {
            if (!mainPresenter.isGameRunning()) {
                mainPresenter.startGame()
                showSnackBar("Starting Production")
            } else {
                showSnackBar("Stopping Production")
                mainPresenter.stopGame()
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun industryUpdatedListener(industry: Industry) {
        industryLevel.text = industry.level.toString()
        industryCost.text = industry.costs.toString()
    }

    override fun trailerGeneratedListener(hub: Hub) {
        resourcesTrailerUnits.text = hub.trailers.size.toString()
    }

    override fun hubUpdatedListener(hub: Hub) {
        hubLevel.text = hub.level.toString()
        hubCost.text = hub.costs.toString()
        hubTrailerMaxSize.text = hub.capacity.toString()
    }

    override fun resourcesGeneratedListener(resources: Resources) {
        metalResources.text = resources.metal.toString()
        fibreResources.text = resources.fibre.toString()
        gasolineResources.text = resources.gasoline.toString()
    }

    override fun errorGameControlerListener(string: String) {
        showSnackBar(string)
    }

    override fun paintFirstState(player: Player) {
        industryUpdatedListener(player.industry)
        hubUpdatedListener(player.hub)
        resourcesGeneratedListener(player.resources)
        trailerGeneratedListener(player.hub)
    }

    private fun showSnackBar(string: String) {
        Snackbar.make(parentView, string, Snackbar.LENGTH_LONG).show()
    }

    override fun onGameOver() {
        startActivity(Intent(this, GameOverActivity::class.java))
    }
}
