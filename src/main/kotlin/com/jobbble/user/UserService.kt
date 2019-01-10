package com.jobbble.user

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService : UserDetailsService {

    @Autowired
    private lateinit var repository: UserRepository

    companion object {
        private const val defaultUsername = "user"
        private const val expirationTime = 864000000
    }

    fun insert(user: User): User? =
            user.copy(password = BCryptPasswordEncoder()
                    .encode(user.password))
                    .let { repository.insert(it) }

    fun all(): List<User> = repository.findAll()

    override fun loadUserByUsername(username: String?): UserDetails =
            (username ?: defaultUsername)
                    .let { repository.findByUsername(it) }


    fun getJsonWebToken(user: User): String = Jwts.builder()
            .setSubject(user.username)
            .setExpiration(
                    Date(System.currentTimeMillis() + expirationTime))
            .signWith(SignatureAlgorithm.HS512, "test".toByteArray())
            .compact()

}