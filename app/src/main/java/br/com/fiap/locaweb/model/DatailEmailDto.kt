package br.com.fiap.locaweb.model

data class DatailEmailDto(
    val subject: String,
    val date: String,
    val body: String,
    val sender: String
)
