package android.afebrerp.com.inngame.domain.usecase

import android.afebrerp.com.inngame.domain.model.base.BaseEntity
import android.afebrerp.com.inngame.domain.model.params.UpdateHubParams
import android.afebrerp.com.inngame.domain.repository.HubRepository
import android.afebrerp.com.inngame.domain.usecase.base.BaseUseCase

class UpdateHubUseCase(private val hubRepository: HubRepository) :
    BaseUseCase<BaseEntity, UpdateHubParams>() {
    override suspend fun buildRepoCall(params: UpdateHubParams): BaseEntity? {
        return hubRepository.upgradeIfAble(params)
    }

    override fun getTag(): String {
        return "UpdateHubUseCase"
    }
}