package com.example.service

import com.example.dao.DepartmentRepository
import com.example.dto.Department
import com.example.exception.DepartmentNotFound
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DepartmentService {
    @Autowired
    lateinit var departmentRepository: DepartmentRepository;

    fun loadAllDepartment(): List<Department> {
        return departmentRepository.findAll()
    }

    fun findDepartmentByID(deptId: String): Department {
        val department = this.departmentRepository.findByDeptID(deptId)
        return department ?: throw DepartmentNotFound(deptId)
    }

}