package example.com.solidgatetask.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "batch")
data class BatchProperties(val userBalanceBatch: Int)
