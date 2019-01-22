package android.afebrerp.com.inngame.domain.usecase

import android.afebrerp.com.inngame.domain.model.base.BaseEntity
import android.afebrerp.com.inngame.domain.model.params.CreateTrailerParams
import android.afebrerp.com.inngame.domain.repository.TrailerRepository
import android.afebrerp.com.inngame.domain.usecase.base.BaseUseCase

class CreateTrailerUseCase(private val trailerRepository: TrailerRepository) :
    BaseUseCase<BaseEntity, CreateTrailerParams>() {
    override suspend fun buildRepoCall(params: CreateTrailerParams): BaseEntity? {
        return trailerRepository.generateIfAble(params)
    }

    override fun getTag(): String {
        return "CreateTrailerUseCase"
    }
}