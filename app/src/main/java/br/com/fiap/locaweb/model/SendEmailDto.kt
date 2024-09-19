package br.com.fiap.locaweb.model

data class SendEmailDto(
    val sender: String?,
    val recipient: String,
    val subject: String,
    val body: String
)
