package com.example.utils

class ResultBean<T> private constructor() {
  var code = 0
  var message: String? = null
  var data: T? = null

  companion object {
    fun <V> error(code: Int, message: String?, data: V?): ResultBean<V> {
      val resultBean: ResultBean<V> = ResultBean()
      resultBean.code = code
      resultBean.message = message
      resultBean.data = data
      return resultBean
    }

    fun <V> error(code: Int, message: String?): ResultBean<V> = error(code, message, null)

    fun success(): ResultBean<*> {
      val resultBean: ResultBean<*> = ResultBean<Any?>()
      resultBean.code = 0
      resultBean.message = "success"
      return resultBean
    }

    fun <V> success(data: V): ResultBean<V> {
      val resultBean: ResultBean<V> = ResultBean()
      resultBean.data = data
      resultBean.code = 0
      resultBean.message = "success"
      return resultBean
    }
  }
}
