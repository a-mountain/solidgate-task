package example.com.solidgatetask.data

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface UserRepository : JpaRepository<User, Int> {
    @Modifying
    @Query("update User u set u.balance = :balance where u.id = :id")
    fun updateBalance(id: Int, balance: Int)
}
