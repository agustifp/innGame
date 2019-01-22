package android.afebrerp.com.inngame.data.repository

import android.afebrerp.com.inngame.data.Constants
import android.afebrerp.com.inngame.data.utils.CostsUtils
import android.afebrerp.com.inngame.domain.model.base.BaseEntity
import android.afebrerp.com.inngame.domain.model.params.UpgradeIndustryParams
import android.afebrerp.com.inngame.domain.repository.IndustryRepository
import kotlinx.coroutines.delay

class IndustryRepositoryImpl : IndustryRepository {

    override suspend fun upgradeIfAble(params: UpgradeIndustryParams): BaseEntity {
        var affordable = false
        if (params.industry.level < params.industry.MAX_LEVEL) {
            affordable = CostsUtils.canAffordCosts(params.industry.costs, params.resources)
            if (affordable) {
                delay(Constants.INDUSTRY_BUILD_TIME)
                CostsUtils.applyCosts(params.industry.costs, params.resources)
                params.industry.level += 1
            }
        }
        return BaseEntity(affordable)
    }
}