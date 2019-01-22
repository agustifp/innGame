package android.afebrerp.com.inngame.data.utils

import android.afebrerp.com.inngame.domain.model.entity.Costs
import android.afebrerp.com.inngame.domain.model.entity.Resources

object CostsUtils {

    fun canAffordCosts(costs: Costs, resources: Resources): Boolean {
        return costs.fibre <= resources.fibre && costs.metal <= resources.metal
    }

    fun applyCosts(costs: Costs, resources: Resources){
        resources.metal -= costs.metal
        resources.fibre -= costs.fibre
    }
}