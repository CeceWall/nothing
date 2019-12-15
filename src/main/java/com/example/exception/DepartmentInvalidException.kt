package com.example.exception

class DepartmentInvalidException(message: String) :BaseException("Department数据异常: $message")