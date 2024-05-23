package example.com.solidgatetask.helper

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.utility.DockerImageName

abstract class BaseDBTest {
    companion object {
        private val db: PostgreSQLContainer<*> = PostgreSQLContainer(DockerImageName.parse("postgres:13.3"))
            .withDatabaseName("test_db")
            .withUsername("root")
            .withPassword("123456")


        @JvmStatic
        @DynamicPropertySource
        fun registerDBContainer(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", db::getJdbcUrl)
            registry.add("spring.datasource.username", db::getUsername)
            registry.add("spring.datasource.password", db::getPassword)
        }

        @JvmStatic
        @BeforeAll
        fun startDBContainer() {
            db.start()
        }

        @AfterAll
        @JvmStatic
        fun stopDBContainer() {
            db.stop()
        }
    }

}
