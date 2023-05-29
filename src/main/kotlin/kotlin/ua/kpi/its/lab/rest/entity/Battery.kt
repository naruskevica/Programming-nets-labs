package ua.kpi.its.lab.rest.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name="battery")
data class Battery(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    val model: String,
    val manufacturer: String,
    val releaseDate: LocalDate,
    val type: String,
    val capacity: Int, // Ah
    val chargeTime: Int, //minutes
    val fastCharge: Boolean,
    @ManyToOne
    @JoinColumn(name = "car_id")
    val car: Car,
) : Comparable<Battery> {
    override fun compareTo(battery: Battery): Int {
        return model.compareTo(battery.model)
    }
}
