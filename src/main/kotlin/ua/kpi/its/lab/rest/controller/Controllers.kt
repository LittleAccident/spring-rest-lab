package ua.kpi.its.lab.rest.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ua.kpi.its.lab.rest.dto.HospitalRequest
import ua.kpi.its.lab.rest.dto.HospitalResponse
import ua.kpi.its.lab.rest.svc.HospitalService

@RestController
@RequestMapping("/hospitals")
class HospitalController @Autowired constructor(
    private val hospitalService: HospitalService
) {
    @GetMapping(path = ["", "/"])
    fun hospitals(): List<HospitalResponse> = hospitalService.read()

    @GetMapping("{id}")
    fun readHospital(@PathVariable("id") id: Long): ResponseEntity<HospitalResponse> {
        return wrapNotFound { hospitalService.readById(id) }
    }

    @PostMapping(path = ["", "/"])
    fun createHospital(@RequestBody hospital: HospitalRequest): HospitalResponse {
        return hospitalService.create(hospital)
    }

    @PutMapping("{id}")
    fun updateHospital(
        @PathVariable("id") id: Long,
        @RequestBody hospital: HospitalRequest
    ): ResponseEntity<HospitalResponse> {
        return wrapNotFound { hospitalService.updateById(id, hospital) }
    }

    @DeleteMapping("{id}")
    fun deleteHospital(@PathVariable("id") id: Long): ResponseEntity<HospitalResponse> {
        return wrapNotFound { hospitalService.deleteById(id) }
    }

    fun <T> wrapNotFound(call: () -> T): ResponseEntity<T> {
        return try {
            val result = call()
            ResponseEntity.ok(result)
        } catch (e: IllegalArgumentException) {
            ResponseEntity.notFound().build()
        }
    }
}
