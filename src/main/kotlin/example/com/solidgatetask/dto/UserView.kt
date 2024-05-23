package example.com.solidgatetask.dto

import example.com.solidgatetask.data.User

data class UserView(
    val id: Int?,
    val name: String,
    val balance: Int,
) {
    companion object {
        fun fromEntity(entity: User) = UserView(
            id = entity.id,
            name = entity.name,
            balance = entity.balance
        )
    }
}
