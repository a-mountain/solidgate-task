package example.com.solidgatetask.service

import example.com.solidgatetask.data.UserRepository
import example.com.solidgatetask.dto.BulkUserBalanceUpdate
import example.com.solidgatetask.dto.UserView
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ContractService(private val userRepository: UserRepository) {
    @Transactional
    fun updateUsersBalance(request: BulkUserBalanceUpdate) {
        for ((id, balance) in request.balances) {
            userRepository.updateBalance(id, balance)
        }
    }

    fun getAllUsers() = userRepository.findAll().map(UserView::fromEntity)
}
