package com.example.lab2

import java.time.LocalDate

data class Battery(
    val model: String,
    val manufacturer: String,
    val releaseDate: LocalDate,
    val type: String,
    val capacity: Int, // Ah
    val chargeTime: Int, //minutes
    val fastCharge: Boolean
) : Comparable<Battery> {
    override fun compareTo(battery: Battery): Int {
        return model.compareTo(battery.model)
    }
}