package com.kotlin.lvicto.ankosample.model

class WinterResort(map: MutableMap<String, Any?>) {

    var name: String by map
    var state: String by map
    var country: String by map
    var ratings: Long by map

    constructor(name: String, state: String, country: String, ratings: Long): this(HashMap()) {
        this.name = name
        this.country = country
        this.state = state
        this.ratings = ratings
    }

    override fun toString(): String {
        return " $name \n $state, $country \n Ratings: $ratings"
    }
}