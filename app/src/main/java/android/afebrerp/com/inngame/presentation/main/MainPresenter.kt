package android.afebrerp.com.inngame.presentation.main

import android.afebrerp.com.inngame.domain.model.entity.Hub
import android.afebrerp.com.inngame.domain.model.entity.Industry
import android.afebrerp.com.inngame.domain.model.entity.Player
import android.afebrerp.com.inngame.domain.model.entity.Resources

interface MainPresenter {
    interface Presenter{
        fun startGame()
        fun paintInitialState()
        fun isGameRunning(): Boolean
        fun stopGame()
    }

    interface View{
        fun industryUpdatedListener(industry: Industry)
        fun trailerGeneratedListener(hub: Hub)
        fun hubUpdatedListener(hub: Hub)
        fun resourcesGeneratedListener(resources: Resources)
        fun errorGameControlerListener(string: String)
        fun paintFirstState(player:Player)
        fun onGameOver()
    }
}