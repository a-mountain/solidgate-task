package example.com.solidgatetask.data

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "users")
class User(@Id var id: Int?, var name: String, var balance: Int)
