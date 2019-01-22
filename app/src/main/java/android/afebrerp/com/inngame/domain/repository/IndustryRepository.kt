package android.afebrerp.com.inngame.domain.repository

import android.afebrerp.com.inngame.domain.model.base.BaseEntity
import android.afebrerp.com.inngame.domain.model.params.UpgradeIndustryParams

interface IndustryRepository {
    suspend fun upgradeIfAble(params: UpgradeIndustryParams): BaseEntity
}
