package android.afebrerp.com.inngame.domain.model.entity

class Player(var resources: Resources, var industry: Industry, var hub: Hub) {

    fun addResources(newResources: Resources) {
        this.resources.fibre += newResources.fibre
        this.resources.metal += newResources.metal
        this.resources.gasoline += newResources.gasoline
    }
}