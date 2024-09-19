package br.com.fiap.locaweb.model

data class EmailDto(
    val id: Int,
    val subject: String,
    val category: String,
    val date: String,
    val read: Boolean,
    val importante: Boolean,
)
