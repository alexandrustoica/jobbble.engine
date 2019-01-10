package com.jobbble.security

import com.jobbble.user.UserRepository
import io.jsonwebtoken.Jwts
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JsonWebTokenAuthorizationFilter(
        manager: AuthenticationManager,
        private val userRepository: UserRepository) :
        BasicAuthenticationFilter(manager) {

    override fun doFilterInternal(
            request: HttpServletRequest,
            response: HttpServletResponse,
            chain: FilterChain) {
        val header = request.getHeader("Authorization") ?: ""
        if (header == "" || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response)
            return
        }
        val authentication = getAuthenticationFromRequest(request)
        SecurityContextHolder.getContext().authentication = authentication
        chain.doFilter(request, response)
    }

    private fun getAuthenticationFromRequest(request: HttpServletRequest):
            UsernamePasswordAuthenticationToken? {
        val token = request.getHeader("Authorization") ?: ""
        val username = Jwts.parser()
                .setSigningKey("test".toByteArray())
                .parseClaimsJws(token.replace("Bearer ", ""))
                .body.subject ?: ""
        return if (username != "") userRepository.findByUsername(username)
                .let {
                    UsernamePasswordAuthenticationToken(
                            it, it?.password, it?.authorities)
                }
        else null
    }
}