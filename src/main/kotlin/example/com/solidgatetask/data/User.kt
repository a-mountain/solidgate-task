package example.com.solidgatetask.data

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.Hibernate
import org.hibernate.proxy.HibernateProxy

@Entity
@Table(name = "users")
class User(@Id var id: Int?, var name: String, var balance: Int) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as User
        return id != null && id == other.id
    }

    override fun hashCode(): Int = 1756406093

    override fun toString() = "User(id=$id, name='$name', balance=$balance)"
}
