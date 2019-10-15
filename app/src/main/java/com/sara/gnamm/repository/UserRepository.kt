package com.sara.gnamm.repository

import com.sara.gnamm.models.user.User

interface UserRepository {
    //FIXME can I have a default db implementation/field?

    fun findAll(): MutableList<User> //fun findAll(): List<User> = println("Call findAll") => this is a default implementation of a method
    fun find(id: String): User
    fun save(user: User): User
    fun update(user: User): User
    fun delete(id: String)
    fun deleteAll()
}
//todo implement in Room https://developer.android.com/topic/libraries/architecture/room

class UserRepositoryMock(private val users: MutableList<User> = mutableListOf()) : UserRepository {

    override fun findAll(): MutableList<User> {
        return users
    }

    override fun find(id: String): User {
        return if (users.filter { id == it.id }.size == 1) users[0] else throw RuntimeException("User not found")
    }

    override fun save(user: User): User {
        users.add(user)
        return find(user.id) //to make sure that the user we've saved is the same as the user we wanted to save
    }

    override fun update(user: User): User {
        val old = find(user.id)
        val idx = users.indexOf(old)
        users[idx] = user
        return users[idx]
    }

    override fun delete(id: String) {
        users.removeIf { id == it.id }
    }

    override fun deleteAll() {
        users.clear()
    }

}