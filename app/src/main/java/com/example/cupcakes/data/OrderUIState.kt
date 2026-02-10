package com.example.cupcakes.data

data class OrderUIState(
    val flavor : String = "",
    val quantity : Int = 0,
    val date : String = "",
    val price : String = "",
    val pickupOptions : List<String> = listOf()
)
