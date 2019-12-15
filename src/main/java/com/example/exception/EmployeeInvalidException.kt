package com.example.exception

class EmployeeInvalidException(message: String) : BaseException("Employee信息错误：$message")