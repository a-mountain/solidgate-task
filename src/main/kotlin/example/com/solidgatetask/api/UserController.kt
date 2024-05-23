package example.com.solidgatetask.api

import example.com.solidgatetask.dto.BulkUserBalanceUpdate
import example.com.solidgatetask.service.ContractService
import example.com.solidgatetask.dto.UserView
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(private val contractService: ContractService) {
    @PostMapping("/users/balance")
    fun setUsersBalance(@RequestBody body: BulkUserBalanceUpdate) {
        contractService.updateUsersBalance(body)
    }

    @GetMapping("/users")
    fun getAllUsers(): List<UserView> = contractService.getAllUsers();
}
