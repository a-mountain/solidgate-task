package example.com.solidgatetask.helper

import example.com.solidgatetask.BulkUserBalanceUpdate
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.postForEntity

class UserApiSupport(private val client: TestRestTemplate) {
    fun setUsersBalance(body: BulkUserBalanceUpdate) = client.postForEntity<Unit>("/users/balance", body)
}
