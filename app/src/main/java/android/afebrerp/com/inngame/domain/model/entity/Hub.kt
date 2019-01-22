package android.afebrerp.com.inngame.domain.model.entity

class Hub {

    val trailers: MutableList<Trailer> = mutableListOf()
    private val INITIAL_METAL_COST = 4000
    private val INITIAL_FIBRE_COST = 1500
    val constructionSeconds: Int = 30
    val costs: Costs =
        Costs(INITIAL_METAL_COST, INITIAL_FIBRE_COST)
    var MAX_LEVEL = 3
    var level:Int = 0
        set(value) {
            if (value <= MAX_LEVEL) {
                field = value
                capacity += 2
                costs.fibre += 10 * costs.fibre / 100
                costs.metal += 10 * costs.metal / 100
            }
        }
    var capacity : Int = 4

}

/**
 * Cost of creation 4000 metals, 1500 fibres
Trailer hub capacity per level 1:3 trailers. The capacity increases by 2 each level.
The cost increases by 10% each level.
Time of construction is 30 seconds at each level.
Max level  is 3.
 */