package com.example.service

import com.example.dao.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserService : UserDetailsService {
    @Autowired
    private val userRepository: UserRepository? = null

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(loginName: String?): UserDetails {
        if (loginName == null) {
            throw UsernameNotFoundException("Username not exists")
        }
        val user = userRepository!!.findByLoginName(loginName)
        return user.orElseThrow<RuntimeException> { throw UsernameNotFoundException("Username not exists") }
    }

    val currentEmployeeId: String
        get() = ""
}