package example.com.solidgatetask.service

import example.com.solidgatetask.data.UserRepository
import example.com.solidgatetask.dto.BulkUserBalanceUpdate
import example.com.solidgatetask.dto.UserView
import org.slf4j.LoggerFactory.getLogger
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ContractService(private val userRepository: UserRepository) {
    companion object {
        @JvmStatic
        private val LOGGER = getLogger(ContractService::class.java)
    }

    fun updateUsersBalance(request: BulkUserBalanceUpdate) {
        LOGGER.info("Start users balance bulk update, size=${request.balances.size}")
        userRepository.bulkBalanceUpdate(request.balances)
        LOGGER.info("End users balance bulk update, size=${request.balances.size}")
    }

    fun getAllUsers() = userRepository.findAll().map(UserView::fromEntity)
}
