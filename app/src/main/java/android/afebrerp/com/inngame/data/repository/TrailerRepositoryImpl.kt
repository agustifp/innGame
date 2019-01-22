package android.afebrerp.com.inngame.data.repository

import android.afebrerp.com.inngame.data.Constants
import android.afebrerp.com.inngame.data.utils.CostsUtils
import android.afebrerp.com.inngame.domain.model.base.BaseEntity
import android.afebrerp.com.inngame.domain.model.entity.Trailer
import android.afebrerp.com.inngame.domain.model.params.CreateTrailerParams
import android.afebrerp.com.inngame.domain.repository.TrailerRepository
import kotlinx.coroutines.delay

class TrailerRepositoryImpl: TrailerRepository {

    override suspend fun generateIfAble(params : CreateTrailerParams):BaseEntity {
        var affordable = false
        val trailer = Trailer()
        affordable = CostsUtils.canAffordCosts(trailer.costs, params.resources)
        if (affordable) {
            CostsUtils.applyCosts(trailer.costs, params.resources)
            //must take one minute to build
            delay(Constants.TRAILER_BUILD_TIME)
            params.hub.trailers.add(trailer)
        }
        return BaseEntity(affordable)
    }
}