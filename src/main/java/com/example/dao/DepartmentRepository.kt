package com.example.dao

import com.example.dto.Department
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DepartmentRepository : JpaRepository<Department, String> {
    fun findByDeptID(deptId: String): Department?
}