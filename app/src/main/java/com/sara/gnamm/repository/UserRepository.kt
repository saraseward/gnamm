package com.sara.gnamm.repository

import com.sara.gnamm.models.User

interface UserRepository {
    //FIXME can I have a default db implementation/field?

    fun findAll(): List<User> //fun findAll(): List<User> = println("Call findAll") => this is a default implementation of a method
    fun find(id: Int): User
    fun save(user: User): User
    fun update(id: Int, user: User): User
    fun delete(id: Int)
    fun deleteAll()
}
//todo implement in Room https://developer.android.com/topic/libraries/architecture/room

class UserRepositoryMock : UserRepository {

    var users = mutableListOf<User>()

    override fun findAll(): List<User> {
        return users
    }

    override fun find(id: Int): User {
        return if (users.filter { id == it.id }.size == 1) users[0] else throw RuntimeException("User not found")
    }

    override fun save(user: User): User {
        users.add(user)
        return find(user.id) //to make sure that the user we've saved is the same as the user we wanted to save
    }

    override fun update(id: Int, user: User): User {
        var old = find(id)
        var idx = users.indexOf(old)
        users[idx] = user
        return users[idx]
    }

    override fun delete(id: Int) {
        users.removeIf { id == it.id }
    }

    override fun deleteAll() {
        users = mutableListOf()
    }

}