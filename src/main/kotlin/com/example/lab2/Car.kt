package com.example.lab2

import java.time.LocalDate

data class Car(
    val brand: String,
    val model: String,
    val manufacturer: String,
    val releaseDate: LocalDate,
    val maxSpeed: Int,
    val price: Int,
    val withABS: Boolean,
    val battery: Battery,
) : Comparable<Car> {
    override fun compareTo(car: Car): Int {
        return brand.compareTo(car.brand)
    }
}