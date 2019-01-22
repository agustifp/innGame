package android.afebrerp.com.inngame.data

import android.afebrerp.com.inngame.domain.model.entity.Hub
import android.afebrerp.com.inngame.domain.model.entity.Industry
import android.afebrerp.com.inngame.domain.model.entity.Player
import android.afebrerp.com.inngame.domain.model.entity.Resources
import android.afebrerp.com.inngame.domain.model.params.CreateTrailerParams
import android.afebrerp.com.inngame.domain.model.params.UpdateHubParams
import android.afebrerp.com.inngame.domain.model.params.UpgradeIndustryParams
import android.afebrerp.com.inngame.domain.usecase.CreateTrailerUseCase
import android.afebrerp.com.inngame.domain.usecase.UpdateHubUseCase
import android.afebrerp.com.inngame.domain.usecase.UpgradeIndustryUseCase
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

object GameController : KoinComponent {

    lateinit var player: Player
    private lateinit var resourcesGeneratedListener: (Resources) -> Unit

    /**
     * @param block function to set and later on answer the state changes
     * */
    fun resourcesGeneratedListener(block: (Resources) -> Unit) {
        resourcesGeneratedListener = block
    }

    private val upgradeIndustryUseCase: UpgradeIndustryUseCase by inject()
    private val upgradeHubUseCase: UpdateHubUseCase by inject()
    private val createTrailerUseCase: CreateTrailerUseCase by inject()

    private lateinit var trailerGeneratedListener: (Hub) -> Unit
    private lateinit var industryUpdatedListener: (Industry) -> Unit
    private lateinit var hubUpdatedListener: (Hub) -> Unit
    private lateinit var errorGameControlerListener: (String) -> Unit
    private lateinit var gameOverListener: (Unit) -> Unit

    /**
     * @param block function to set and later on answer the state changes
     * */
    fun industryUpdatedListener(block: (Industry) -> Unit) {
        industryUpdatedListener = block
    }

    /**
     * @param block function to set and later on answer the state changes
     * */
    fun hubUpdatedListener(block: (Hub) -> Unit) {
        hubUpdatedListener = block
    }

    /**
     * @param block function to set and later on answer the state changes
     * */
    fun trailerGeneratedListener(block: (Hub) -> Unit) {
        trailerGeneratedListener = block
    }

    /**
     * @param block function to set and later on answer the state changes
     * */
    fun errorGameControlerListener(block: (String) -> Unit) {
        errorGameControlerListener = block
    }

    /**
     * @param block function to set and later on answer the state changes
     * */
    fun gameOverListener(block: (Unit) -> Unit) {
        gameOverListener = block
    }

    var isGameRunning: Boolean = false

    private var isPlayerAtInitialState = false

    fun startGame() {

        isGameRunning = true
        if(!isPlayerAtInitialState) {
            isPlayerAtInitialState = true
            this.player = getInitialPlayerState()
        }
        startProduction(this.player) {
            if (it) resourcesGeneratedListener(this.player.resources)
        }
    }

    fun getInitialPlayerState() = Player(
        Resources(
            Constants.INITIAL_METAL_RESOURCE,
            Constants.INITIAL_FIBRE_RESOURCE,
            Constants.INITIAL_GASOLINE_RESOURCE
        ),
        Industry(),
        Hub()
    )

    private fun startProduction(player: Player, block: (Boolean) -> Unit) {
        player.industry.startProduction { newResources ->
            player.addResources(newResources)
            block(true)
        }
    }

    fun stopProduction() {
        isGameRunning = false
        this.player.industry.stopProduction()
    }

    fun checkForUpdates() {
        if (this.player.hub.trailers.size < Constants.MAX_TRAILER_COUNT_GAME) {
            when {
                this.player.industry.level < this.player.industry.MAX_LEVEL -> upgradeIndustry()
                this.player.hub.trailers.size < this.player.hub.capacity -> generateTrailer()
                else -> updateHub()
            }
        } else {
            stopProduction()
            gameOverListener(Unit)
        }
    }

    private var industryOnProgress: Boolean = false
    private var trailerOnProgress: Boolean = false
    private var hubOnProgress: Boolean = false

    private fun upgradeIndustry() {
        if (!industryOnProgress) {
            industryOnProgress = true
            upgradeIndustryUseCase.executeAsync(UpgradeIndustryParams(this.player.resources, this.player.industry),
                { baseEntity ->
                    industryOnProgress = false
                    baseEntity?.result.let { if (it == true) industryUpdatedListener(this.player.industry) }
                },
                { it ->
                    it?.let { errorGameControlerListener(it.customMessage) }
                })
        }
    }

    private fun generateTrailer() {
        if (!trailerOnProgress) {
            trailerOnProgress = true
            createTrailerUseCase.executeAsync(CreateTrailerParams(this.player.resources, this.player.hub),
                { baseEntity ->
                    trailerOnProgress = false
                    baseEntity?.result.let { if (it == true) trailerGeneratedListener(this.player.hub) }
                }, { it ->
                    it?.let { errorGameControlerListener(it.customMessage) }
                })
        }
    }

    private fun updateHub() {
        if (!hubOnProgress) {
            hubOnProgress = true
            upgradeHubUseCase.executeAsync(UpdateHubParams(this.player.resources, this.player.hub), { baseEntity ->
                hubOnProgress = false
                baseEntity?.result.let { if (it == true) hubUpdatedListener(this.player.hub) }
            }, { it ->
                it?.let { errorGameControlerListener(it.customMessage) }
            })
        }
    }
}