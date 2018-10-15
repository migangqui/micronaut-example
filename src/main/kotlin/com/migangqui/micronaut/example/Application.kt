package com.migangqui.micronaut.example

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("com.migangqui.micronaut.example")
                .mainClass(Application.javaClass)
                .start()
    }
}