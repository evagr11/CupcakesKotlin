package com.example.cupcakes.data

data class OrderUIState(
    val flavor : String,
    val quantity : Int,
    val date : String,
    val price : Double,
    val pickupOptions : List<String>
)
