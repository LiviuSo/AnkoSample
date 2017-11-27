package com.kotlin.lvicto.ankosample.model

object WinterResortsList {

    fun getResorts(): List<String> {
        val resorts: ArrayList<String> = arrayListOf()
        resorts.add(WinterResort("49 Degree", "Washington", "USA", 56).toString())
        resorts.add(WinterResort("Afton Alps", "Minnesota", "USA", 45).toString())
        resorts.add(WinterResort("Alpental", "Snoqualmie Pass", "USA", 42).toString())
        resorts.add(WinterResort("Alpine Valley", "Ohio", "USA", 21).toString())
        resorts.add(WinterResort("Alpine Valley Resort", "Wisconsin", "USA", 62).toString())
        resorts.add(WinterResort("Alta Ski Area", "Utah", "USA", 118).toString())
        resorts.add(WinterResort("Alyeska Resort", "Alaska", "USA", 37).toString())
        resorts.add(WinterResort("Andes Tower Hills Ski Area", "Minnesota", "USA", 13).toString())
        resorts.add(WinterResort("Angel Fire Resort", "New Mexico", "USA", 96).toString())
        resorts.add(WinterResort("Anthony Lakes Mountain Resort", "Oregon", "USA", 12).toString())
        resorts.add(WinterResort("Apex Mountain", "British Columbia", "CAN", 20).toString())
        resorts.add(WinterResort("Big White", "Britissh Columbia", "CAN", 47).toString())
        resorts.add(WinterResort("Blue Mountain", "Ontario", "CAN", 37).toString())
        resorts.add(WinterResort("Boston Hills", "Ohio", "USA", 37).toString())
        resorts.add(WinterResort("Breckenridge", "Colorado", "USA", 165).toString())
        resorts.add(WinterResort("Calabogie Peaks", "Ontario", "CAN", 14).toString())
        resorts.add(WinterResort("Buffalo Ski Club Ski Area", "New York", "USA", 3).toString())
        resorts.add(WinterResort("Great Divide", "Montana", "USA", 23).toString())
        resorts.add(WinterResort("Grouse Mountain", "British Columbia", "CAN", 17).toString())
        resorts.add(WinterResort("Hidden Valey", "Ontario", "CAN", 2).toString())
        resorts.add(WinterResort("Keystone", "Colorado", "USA", 121).toString())
        resorts.add(WinterResort("Killington Resort", "Vermont", "USA", 176).toString())
        resorts.add(WinterResort("Le Massif", "Quebec", "CAN", 21).toString())
        resorts.add(WinterResort("Mad River Mountain", "Ohio", "USA", 71).toString())

        return resorts
    }
}