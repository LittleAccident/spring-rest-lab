package ua.kpi.its.lab.rest.repo

import org.springframework.data.jpa.repository.JpaRepository
import ua.kpi.its.lab.rest.entity.Hospital
import ua.kpi.its.lab.rest.entity.Medicine

interface HospitalRepository : JpaRepository<Hospital, Long> {
}

interface MedicineRepository : JpaRepository<Medicine, Long> {
}
