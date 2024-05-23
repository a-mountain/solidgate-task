package example.com.solidgatetask.data

interface CustomUserRepository {
    fun bulkBalanceUpdate(balances: Map<Int, Int>)
}
