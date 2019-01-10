package com.jobbble.security

class AccountCredentials(val username: String,
                         val password: String) {
    constructor(): this("", "")
}