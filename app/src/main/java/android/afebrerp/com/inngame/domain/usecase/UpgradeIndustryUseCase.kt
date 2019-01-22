package android.afebrerp.com.inngame.domain.usecase

import android.afebrerp.com.inngame.domain.model.base.BaseEntity
import android.afebrerp.com.inngame.domain.model.params.UpgradeIndustryParams
import android.afebrerp.com.inngame.domain.repository.IndustryRepository
import android.afebrerp.com.inngame.domain.usecase.base.BaseUseCase

class UpgradeIndustryUseCase(private val industryRepository: IndustryRepository) :
    BaseUseCase<BaseEntity, UpgradeIndustryParams>() {
    override suspend fun buildRepoCall(params: UpgradeIndustryParams): BaseEntity? {
        return industryRepository.upgradeIfAble(params)
    }

    override fun getTag(): String {
        return "UpgradeIndustryUseCase"
    }
}