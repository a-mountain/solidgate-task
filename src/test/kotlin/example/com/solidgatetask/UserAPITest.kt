package example.com.solidgatetask

import example.com.solidgatetask.helper.BaseDBTest
import example.com.solidgatetask.helper.UserApiSupport
import example.com.solidgatetask.dto.BulkUserBalanceUpdate
import example.com.solidgatetask.data.UserRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserAPITest(
    @Autowired val client: TestRestTemplate,
    @Autowired val userRepository: UserRepository,
) : BaseDBTest() {

    var userAPI = UserApiSupport(client)

    @Test
    fun `setUsersBalance should update users balances`() {
        val bulkUpdateRequest = BulkUserBalanceUpdate(
            mapOf(
                1 to 100,
                2 to 200,
                3 to 300,
            )
        )

        userAPI.setUsersBalance(bulkUpdateRequest)

        assertUsersBalancesAreUpdated(bulkUpdateRequest)
    }

    fun assertUsersBalancesAreUpdated(bulkUpdateRequest: BulkUserBalanceUpdate) {
        for ((id, expectedBalance) in bulkUpdateRequest.balances) {
            val actualBalance = userRepository.findById(id).get().balance
            assertEquals(expectedBalance, actualBalance)
        }
    }
}
