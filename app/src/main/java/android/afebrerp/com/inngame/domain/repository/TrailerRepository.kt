package android.afebrerp.com.inngame.domain.repository

import android.afebrerp.com.inngame.domain.model.base.BaseEntity
import android.afebrerp.com.inngame.domain.model.params.CreateTrailerParams

interface TrailerRepository {
    suspend fun generateIfAble(params: CreateTrailerParams): BaseEntity
}
