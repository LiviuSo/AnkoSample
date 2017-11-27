package com.kotlin.lvicto.ankosample.model

data class WinterResort(var name: String,
                        private var state: String,
                        private var country: String,
                        private var reviews: Int) {

    override fun toString(): String {
        return " $name \n $state, $country \n Ratings: $reviews"
    }
}