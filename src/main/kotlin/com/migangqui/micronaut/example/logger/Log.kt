package com.migangqui.micronaut.example.logger

import mu.KotlinLogging

object Log {

    val logger = KotlinLogging.logger {}

    fun info(message: String) {
        logger.info { message }
    }

    fun debug(message: String) {
        logger.debug{ message }
    }

    fun error(message: String) {
        logger.error { message }
    }
}