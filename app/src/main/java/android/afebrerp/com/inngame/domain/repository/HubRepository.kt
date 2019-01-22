package android.afebrerp.com.inngame.domain.repository

import android.afebrerp.com.inngame.domain.model.base.BaseEntity
import android.afebrerp.com.inngame.domain.model.params.UpdateHubParams

interface HubRepository {
    suspend fun upgradeIfAble(params: UpdateHubParams): BaseEntity
}
