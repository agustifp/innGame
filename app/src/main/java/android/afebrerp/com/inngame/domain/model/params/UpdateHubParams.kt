package android.afebrerp.com.inngame.domain.model.params

import android.afebrerp.com.inngame.domain.model.entity.Hub
import android.afebrerp.com.inngame.domain.model.entity.Resources
import android.afebrerp.com.inngame.domain.model.params.base.BaseParams

class UpdateHubParams(var resources: Resources, var hub: Hub) : BaseParams()