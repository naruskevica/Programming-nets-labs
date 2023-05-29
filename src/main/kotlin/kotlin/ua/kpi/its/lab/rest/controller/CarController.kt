package ua.kpi.its.lab.rest.controller;

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ua.kpi.its.lab.rest.dto.CarRequest
import ua.kpi.its.lab.rest.dto.CarResponse
import ua.kpi.its.lab.rest.svc.impl.CarServiceImpl


@RestController
@RequestMapping("/car")
class CarController {

    private final lateinit var carServiceImpl: CarServiceImpl;

    @Autowired
    fun CapController(carServiceImpl: CarServiceImpl) {
        this.carServiceImpl = carServiceImpl;
    }

    @GetMapping("/{id}")
    fun getCarById(@PathVariable Long id): ResponseEntity<CarResponse>? {
        val response: CarResponse = carServiceImpl.getCarById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/")
    fun createCar(@Valid @RequestBody carRequest: CarRequest?): ResponseEntity<CarResponse>? {
        val response: CarResponse? = carRequest?.let {
            carServiceImpl.createCar(it)
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    @PutMapping("/{id}")
    fun updateCarById(
        @PathVariable id: Long,
        @Valid @RequestBody carRequest: CarRequest?
    ): ResponseEntity<CarResponse>? {
        val response: CarResponse? = carRequest?.let {
            carServiceImpl.updateCarById(id, it)
        }
        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/{id}")
    fun deleteCarById(@PathVariable id: Long): ResponseEntity<Void?>? {
        val successful: Boolean = carServiceImpl.deleteCarById(id)
        if (successful) {
            println("Car with id $id deleted successfully")
        } else {
            println("Car with id $id not found")
        }
        return ResponseEntity.noContent().build()
    }
}