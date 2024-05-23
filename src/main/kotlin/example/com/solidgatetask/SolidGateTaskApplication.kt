package example.com.solidgatetask

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*

@SpringBootApplication
class SolidGateTaskApplication

data class BulkUserBalanceUpdate(val balances: Map<Int, Int>)

@Entity
@Table(name = "users")
class User(@Id var id: Int?, var name: String, var balance: Int)

interface UserRepository : JpaRepository<User, Int> {
    @Modifying
    @Query("update User u set u.balance = :balance where u.id = :id")
    fun updateBalance(id: Int, balance: Int)
}

@Service
class ContractService(private val userRepository: UserRepository) {
    @Transactional
    fun updateUsersBalance(request: BulkUserBalanceUpdate) {
        for ((id, balance) in request.balances) {
            userRepository.updateBalance(id, balance)
        }
    }

    fun getAllUsers(): List<User> {
        return userRepository.findAll();
    }
}

@RestController
class UserController(private val contractService: ContractService) {
    @PostMapping("/users/balance")
    fun setUsersBalance(@RequestBody body: BulkUserBalanceUpdate) {
        contractService.updateUsersBalance(body)
    }

    @GetMapping("/users")
    fun getAllUser(): List<User> {
        return contractService.getAllUsers();
    }
}

fun main(args: Array<String>) {
	runApplication<SolidGateTaskApplication>(*args)
}
