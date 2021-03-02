package kz3r.ppfresetada

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class PpfResetadaApplication

fun main(args: Array<String>) {
	runApplication<PpfResetadaApplication>(*args)
}


