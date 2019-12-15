package com.example.controller.advice

import com.example.exception.BaseException
import com.example.utils.ResultBean
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.lang.Exception
import java.lang.RuntimeException
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class GlobalControllerExceptionAdvice : ResponseEntityExceptionHandler() {

    override fun handleMethodArgumentNotValid(exception: MethodArgumentNotValidException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        val errors = exception.bindingResult.fieldErrors
                .map { fieldError: FieldError -> fieldError.field + ": " + fieldError.defaultMessage }
        return ResponseEntity.badRequest()
                .body(ResultBean.error(400, "参数校验错误", errors))
    }

    @ExceptionHandler(Exception::class)
    @ResponseBody
    fun handleBaseException(request: HttpServletRequest, ex: Exception): ResultBean<Unit> {
        if (ex is BaseException) {
            logger.debug("发生了业务异常", ex)
            return ResultBean.error<Unit>(400, ex.message)
        }

        logger.error("发生了内部异常", ex)
        return ResultBean.error<Unit>(400, "发生了内部错误")
    }
}
