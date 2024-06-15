package br.com.fiap.locaweb.component.email

data class Email(
    val id: Int,
    val categoria: String,
    val marcadores: List<String>,
    val conteudo: String,
    val horario: String,
    val lido: Boolean,
    var importante: Boolean = false
)

object EmailsOnline{
    val emailsSincronizado = listOf(
    Email(1, "Social", listOf("Pessoal"), "Email de uma rede social", "10:00", true),
    Email(2, "Promoções", listOf("Trabalho"), "Oferta especial para você", "12:30", false),
    Email(3, "Atualizações", listOf("Urgente"), "Atualização do sistema", "14:15", true),
    Email(4, "Social", listOf("Pessoal"), "Mensagem de um amigo", "15:45", false),
    Email(5, "Social", listOf("Pessoal"), "Email de um amigo", "22:00", true),
    Email(6, "Promoções", listOf("Spam"), "Garanta seu lugar ", "12:30", false),
    Email(7, "Atualizações", listOf("Urgente" ,"Trabalho" ), "Atualização do sistema", "14:15", true),
    Email(8, "Social", listOf("Pessoal"), "Mensagem de um Familiar", "15:45", false),
    Email(9, "Promoções", listOf("Spam"), "Venha conhcer as Promoções para o dia de Hoje", "15:45", false) ,
    Email(10, "Promoções", listOf("Spam"), "Venha conhcer as Promoções para o dia de Hoje", "20:45", false),
    Email(11, "Promoções", listOf("Spam"), "Venha conhcer as Promoções para o dia de Hoje", "22:45", false),
    )
}



object EmailsOffline{
    val ultimosEmailsSincronizados = listOf(
        Email(1, "Social", listOf("Pessoal"), "Email de uma rede social", "10:00", true),
        Email(2, "Promoções", listOf("Trabalho"), "Oferta especial para você", "12:30", false),
        Email(3, "Atualizações", listOf("Urgente"), "Atualização do sistema", "14:15", true),
        Email(4, "Social", listOf("Pessoal"), "Mensagem de um amigo", "15:45", false),
        Email(5, "Social", listOf("Pessoal"), "Email de um amigo", "22:00", true),
        Email(6, "Promoções", listOf("Spam"), "Garanta seu lugar ", "12:30", false),
        Email(7, "Atualizações", listOf("Urgente" ,"Trabalho" ), "Atualização do sistema", "14:15", true),
        Email(8, "Social", listOf("Pessoal"), "Mensagem de um Familiar", "15:45", false),
        Email(9, "Promoções", listOf("Spam"), "Venha conhcer as Promoções para o dia de Hoje", "15:45", false) ,
        Email(10, "Promoções", listOf("Spam"), "Venha conhcer as Promoções para o dia de Hoje", "20:45", false),
        Email(11, "Promoções", listOf("Spam"), "Venha conhcer as Promoções para o dia de Hoje", "22:45", false),
    )
}

