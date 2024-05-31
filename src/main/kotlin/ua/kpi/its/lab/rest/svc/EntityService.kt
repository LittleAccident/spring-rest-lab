package ua.kpi.its.lab.rest.svc

import ua.kpi.its.lab.rest.dto.HospitalRequest
import ua.kpi.its.lab.rest.dto.HospitalResponse

interface HospitalService {
    fun create(hospital: HospitalRequest): HospitalResponse
    fun read(): List<HospitalResponse>
    fun readById(id: Long): HospitalResponse
    fun updateById(id: Long, hospital: HospitalRequest): HospitalResponse
    fun deleteById(id: Long): HospitalResponse
}
