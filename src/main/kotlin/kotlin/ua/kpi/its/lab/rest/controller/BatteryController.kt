package ua.kpi.its.lab.rest.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ua.kpi.its.lab.rest.dto.BatteryRequest
import ua.kpi.its.lab.rest.dto.BatteryResponse
import ua.kpi.its.lab.rest.svc.impl.BatteryServiceImpl

@RestController
@RequestMapping("/battery")
class BatteryController(private val batteryServiceImpl: BatteryServiceImpl) {
    @PostMapping
    fun createBattery(@RequestBody req: BatteryRequest): ResponseEntity<BatteryResponse> {
        val response = batteryServiceImpl.createBattery(req)
        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getBatteryById(@PathVariable id: Long): ResponseEntity<BatteryResponse> {
        val response = batteryServiceImpl.getBatteryById(id)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @PutMapping("/{id}")
    fun updateBatteryById(@PathVariable id: Long, @RequestBody req: BatteryRequest): ResponseEntity<BatteryResponse> {
        val response = batteryServiceImpl.updateBatteryById(id, req)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteBatteryById(@PathVariable id: Long): ResponseEntity<Void> {
        batteryServiceImpl.deleteBatteryById(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}