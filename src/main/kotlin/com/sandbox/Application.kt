package com.sandbox

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import reactor.blockhound.BlockHound

@SpringBootApplication
class Application {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            BlockHound.install()
            SpringApplication.run(Application::class.java, *args)
        }
    }
}