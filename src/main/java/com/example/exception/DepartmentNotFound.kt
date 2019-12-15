package com.example.exception

class DepartmentNotFound(deptId: String) : BaseException("部门未找到, id: $deptId")

