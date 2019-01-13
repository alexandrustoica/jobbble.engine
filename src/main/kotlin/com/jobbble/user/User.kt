package com.jobbble.user

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.PersistenceConstructor
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Document
@JsonIgnoreProperties(ignoreUnknown = true)
data class User
@PersistenceConstructor constructor(
        @JsonIgnore @Id val id: ObjectId = ObjectId(),
        private val username: String = "default",
        private val password: String = "default",
        private val name: String = "John",
        private val profileImageUrl: String = "",
        private val age: String = "",
        @JsonIgnore @DBRef private val skills: List<Skill> = listOf(),
        private val role: UserRole = UserRole.STUDENT) : UserDetails {

    override fun isCredentialsNonExpired(): Boolean = true
    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
    override fun isEnabled(): Boolean = true

    override fun getAuthorities():
            MutableCollection<out GrantedAuthority> = mutableListOf(
            SimpleGrantedAuthority(
                    role.toString()),
            SimpleGrantedAuthority(
                    UserRole.HR.toString()))

    override fun getPassword(): String = password
    override fun getUsername(): String = username

    @JsonProperty("skills")
    fun skills(): List<Skill> = skills

    @JsonProperty("id")
    fun id(): String = id.toString()
}