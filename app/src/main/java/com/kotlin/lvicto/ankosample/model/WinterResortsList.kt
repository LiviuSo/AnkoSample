package com.kotlin.lvicto.ankosample.model

object WinterResortsList {

    val resorts = arrayListOf(
                    WinterResort("49 Degree", "Washington", "USA", 56),
                    WinterResort("Afton Alps", "Minnesota", "USA", 45),
                    WinterResort("Alpental", "Snoqualmie Pass", "USA", 42),
                    WinterResort("Alpine Valley", "Ohio", "USA", 21),
                    WinterResort("Alpine Valley Resort", "Wisconsin", "USA", 62),
                    WinterResort("Alta Ski Area", "Utah", "USA", 118),
                    WinterResort("Alyeska Resort", "Alaska", "USA", 37),
                    WinterResort("Andes Tower Hills Ski Area", "Minnesota", "USA", 13),
                    WinterResort("Angel Fire Resort", "New Mexico", "USA", 96),
                    WinterResort("Anthony Lakes Mountain Resort", "Oregon", "USA", 12),
                    WinterResort("Apex Mountain", "British Columbia", "CAN", 20),
                    WinterResort("Big White", "British Columbia", "CAN", 47),
                    WinterResort("Blue Mountain", "Ontario", "CAN", 37),
                    WinterResort("Boston Hills", "Ohio", "USA", 37),
                    WinterResort("Breckenridge", "Colorado", "USA", 165),
                    WinterResort("Calabogie Peaks", "Ontario", "CAN", 14),
                    WinterResort("Buffalo Ski Club Ski Area", "New York", "USA", 3),
                    WinterResort("Great Divide", "Montana", "USA", 23),
                    WinterResort("Grouse Mountain", "British Columbia", "CAN", 17),
                    WinterResort("Hidden Valey", "Ontario", "CAN", 2),
                    WinterResort("Keystone", "Colorado", "USA", 121),
                    WinterResort("Killington Resort", "Vermont", "USA", 176),
                    WinterResort("Le Massif", "Quebec", "CAN", 21),
                    WinterResort("Mad River Mountain", "Ohio", "USA", 71))

    fun getResortsString(): List<String> {
        val strings: ArrayList<String> = arrayListOf()
        resorts.mapTo(strings) { it.toString() }
        return strings
    }

    fun getResortsString(resorts: List<WinterResort>): List<String> {
        val strings: ArrayList<String> = arrayListOf()
        resorts.mapTo(strings) { it.toString() }
        return strings
    }

}