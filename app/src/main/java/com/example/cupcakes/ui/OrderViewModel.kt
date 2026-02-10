package com.example.cupcakes.ui

import androidx.lifecycle.ViewModel
import com.example.cupcakes.data.OrderUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

private const val PRICE_PER_CUPCAKE = 2.00
private const val PRICE_FOR_SAME_DAY_PICKUP = 3.00

class OrderViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(OrderUIState(pickupOptions = pickupOptions()))
    val uiState : StateFlow<OrderUIState> = _uiState.asStateFlow()

    fun setQuantity(cupcakeAmount: Int){
        _uiState.update { currentState ->
            currentState.copy(
                quantity = cupcakeAmount,
                price = calculatePrice(cupcakeAmount)
            )

        }


    }

    fun setFlavour(flavour : String){
        _uiState.update { currentState ->
            currentState.copy(flavor = flavour)
        }

    }

    fun setDate(pickupDate : String){
        _uiState.update { currentState ->
            currentState.copy(
                date = pickupDate,
                price = calculatePrice(pickupDate = pickupDate)
            )
        }
    }

    fun calculatePrice(quantity : Int = _uiState.value.quantity, pickupDate: String = _uiState.value.date) : String{
        var totalAmount : Double = quantity * PRICE_PER_CUPCAKE

        if(pickupOptions()[0] == pickupDate){
            totalAmount += PRICE_FOR_SAME_DAY_PICKUP
        }

        return NumberFormat.getCurrencyInstance().format(totalAmount)

    }

    fun pickupOptions() : List<String>{
        val dateOptions = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()

        repeat(4){
            dateOptions.add(formatter.format((calendar.time)))
            calendar.add(Calendar.DATE, 1)
        }

        return dateOptions

    }
}