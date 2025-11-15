package cl.duoc.luditest_final.security

import at.favre.lib.crypto.bcrypt.BCrypt

object SecurityConfig {

    // Usar BCrypt para hashing seguro
    fun hashPassword(password: String): String {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray())
    }

    fun verifyPassword(password: String, hashedPassword: String): Boolean {
        return BCrypt.verifyer().verify(password.toCharArray(), hashedPassword).verified
    }
}