package com.example.utils

import com.example.exception.BaseException
import com.sun.istack.Nullable
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

@Component
class TimeUtils {
    companion object {
        var timeUtils: TimeUtils = TimeUtils()
            @Bean get() = field

    }

    val timeMin: LocalDateTime = LocalDate.parse("1970-01-01").atStartOfDay()
    val timeMax: LocalDateTime = LocalDate.parse("2099-12-31").atStartOfDay()

    fun format(time: LocalDateTime): String {
        return time.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    fun dateToDateTime(@Nullable timeString: String?, time: LocalTime? = null): LocalDateTime? {
        if (timeString == null || timeString.isEmpty() || timeString.isBlank()) {
            return null
        }
        try {
            var destTime = time;
            if (time == null) {
                destTime = LocalTime.of(0, 0, 0, 0)
            }
            return LocalDate.parse(timeString, DateTimeFormatter.ISO_DATE).atTime(destTime)
        } catch (e: DateTimeParseException) {
            throw BaseException("${timeString}时间格式错误")
        }
    }

    fun parse(timeString: String): LocalDateTime {
        try {
            return LocalDateTime.parse(timeString);
        } catch (e: DateTimeParseException) {
            throw BaseException("${timeString}时间格式错误")
        }
    }
}
