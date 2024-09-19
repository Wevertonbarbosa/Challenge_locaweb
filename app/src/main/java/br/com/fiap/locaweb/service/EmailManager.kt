package br.com.fiap.locaweb.service

object EmailManager {

    private var email: String? = null

    fun saveEmail(newEmail: String) {
        email = newEmail
    }

    fun getEmail(): String? {
        return email
    }

    fun clearEmail() {
        email = null
    }
}