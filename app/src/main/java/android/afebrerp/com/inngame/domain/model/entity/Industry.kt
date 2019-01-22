package android.afebrerp.com.inngame.domain.model.entity

import android.afebrerp.com.inngame.data.Constants
import android.os.CountDownTimer

class Industry {

    private val INITIAL_METAL_COST = 1500
    private val INITIAL_FIBRE_COST = 500
    val costs: Costs = Costs(INITIAL_METAL_COST, INITIAL_FIBRE_COST)
    var MAX_LEVEL = 4
    var level: Int = 1
        set(value) {
            if (value <= MAX_LEVEL) {
                field = value
                costs.fibre += 10 * costs.fibre / 100
                costs.metal += 10 * costs.metal / 100
            }
        }

    private lateinit var productionTimer: CountDownTimer

    fun startProduction(block: (Resources) -> Unit) {
        productionTimer = object : CountDownTimer(Long.MAX_VALUE, Constants.CONSTRUCTION_SECONDS) {
            override fun onTick(millisUntilFinished: Long) {
                block(getDefaultProductionResources())
            }

            override fun onFinish() {}
        }
        productionTimer.start()
    }

    private fun getDefaultProductionResources(): Resources {
        return Resources(2000, 1000, 20)
    }

    fun stopProduction() {
        productionTimer.cancel()
    }
}


/**
 * Cost of creation 1500 metals, 500 fibres.
The cost increases by 10% per level.
Time of construction 30 seconds each level.
Max level is 4
 */


/**
 * Your Industry generate 2000 metals, 1000 fibre and 20 gasoline every minute.
 */