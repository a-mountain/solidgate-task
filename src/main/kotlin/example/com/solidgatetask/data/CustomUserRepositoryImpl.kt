package example.com.solidgatetask.data

import example.com.solidgatetask.properties.BatchProperties
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.transaction.annotation.Transactional

open class CustomUserRepositoryImpl(
    private val template: JdbcTemplate,
    private val properties: BatchProperties
) : CustomUserRepository {

    @Transactional
    override fun bulkBalanceUpdate(balances: Map<Int, Int>) {
        template.batchUpdate(
            "UPDATE users SET balance = ? WHERE id = ?",
            balances.keys,
            properties.userBalanceBatch
        ) { statement, id ->
            val balance = balances[id]!!
            statement.setInt(1, balance)
            statement.setInt(2, id)
        }
    }
}
