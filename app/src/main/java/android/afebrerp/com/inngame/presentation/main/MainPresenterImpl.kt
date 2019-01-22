package android.afebrerp.com.inngame.presentation.main

import android.afebrerp.com.inngame.data.GameController
import android.util.Log

class MainPresenterImpl(val view: MainPresenter.View) : MainPresenter.Presenter {
    override fun stopGame() {
        gameController.stopProduction()
    }

    private val gameController = GameController
    private val TAG = "Presenter"

    override fun paintInitialState(){
        view.paintFirstState(gameController.getInitialPlayerState())
    }
    override fun startGame() {
        gameController.startGame()
        setListeners()
        checkForUpdateLogic()
    }

    override fun isGameRunning() = gameController.isGameRunning

    private fun setListeners() {
        gameController.industryUpdatedListener { industry ->
            Log.d(TAG, "Industry upgraded to level: ${industry.level}")
            checkForUpdateLogic()
            view.industryUpdatedListener(industry)
        }

        gameController.trailerGeneratedListener {
            Log.d(TAG, "New Trailer generated: ${it.trailers.size}")
            checkForUpdateLogic()
            view.trailerGeneratedListener(it)
        }

        gameController.hubUpdatedListener {
            Log.d(TAG, "Hub upgraded to level: ${it.level}")
            checkForUpdateLogic()
            view.hubUpdatedListener(it)
        }

        gameController.resourcesGeneratedListener {
            Log.d(TAG, "Actual resources: metal = ${it.metal} fibre = ${it.fibre}")
            checkForUpdateLogic()
            view.resourcesGeneratedListener(it)
        }

        gameController.errorGameControlerListener {
            Log.d(TAG, "Error : $it")
            view.errorGameControlerListener(it)
        }

        gameController.gameOverListener {
            view.onGameOver()
        }
    }


    private fun checkForUpdateLogic() {
        gameController.checkForUpdates()
    }
}