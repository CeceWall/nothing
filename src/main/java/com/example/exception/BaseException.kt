package com.example.exception

import java.lang.RuntimeException

open class BaseException(override val message: String?) : RuntimeException(message)