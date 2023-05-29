package com.example.lab2

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import java.time.LocalDate

@SpringBootApplication
class Main

fun main() {
    val carContainer = AnnotationConfigApplicationContext(AppConfig::class.java).getBean(Container::class.java) as CarContainer

    carContainer.add(
        Car(
            "Tesla",
            "Model S",
            "USA",
            LocalDate.of(2012, 1, 1),
            200,
            50000,
            true,
            Battery("1598486-00-F", "Tesla", LocalDate.of(2015, 1, 1), "LI-ION", 50, 100, true),
        ),
    )

    carContainer.add(
        Car(
            "Tesla",
            "Model 3",
            "USA",
            LocalDate.of(2016, 1, 1),
            180,
            36000,
            true,
            Battery("1598486-00-D", "Tesla", LocalDate.of(2017, 1, 1), "LI-ION", 50, 80, true),
        ),
    )
}
