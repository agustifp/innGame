package android.afebrerp.com.inngame.data.repository

import android.afebrerp.com.inngame.data.Constants
import android.afebrerp.com.inngame.data.utils.CostsUtils
import android.afebrerp.com.inngame.domain.model.base.BaseEntity
import android.afebrerp.com.inngame.domain.model.params.UpdateHubParams
import android.afebrerp.com.inngame.domain.repository.HubRepository
import kotlinx.coroutines.delay

class HubRepositoryImpl : HubRepository {

    override suspend fun upgradeIfAble(params: UpdateHubParams): BaseEntity {
        var affordable = false
        if (params.hub.level < params.hub.MAX_LEVEL) {
            affordable = CostsUtils.canAffordCosts(params.hub.costs, params.resources)
            if (affordable) {
                delay(Constants.HUB_BUILD_TIME)
                CostsUtils.applyCosts(params.hub.costs, params.resources)
                params.hub.level += 1
            }
        }
        return BaseEntity(affordable)
    }
}