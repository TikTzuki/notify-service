package vn.unicloud.notification.utils

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class Logging {
}

inline fun <reified T> logger(): Logger {
    return LoggerFactory.getLogger(T::class.java)
}