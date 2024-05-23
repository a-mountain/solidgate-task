package example.com.solidgatetask

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan("example.com.solidgatetask.properties")
@SpringBootApplication
class SolidGateTaskApplication

fun main(args: Array<String>) {
	runApplication<SolidGateTaskApplication>(*args)
}
