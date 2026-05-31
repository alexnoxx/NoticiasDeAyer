package ar.edu.unsam.algo2

import java.time.LocalDate
import java.time.Period

abstract class Noticia(
                        val codNoticia: String,
                        val fechaEnQueFueEscrito: LocalDate,
                        val periodistaAsociado: Periodista,
                        val gradoImportancia: Int,
                        val titulo: String,
                        val desarrollo: String
                        //val linkAOtrasNoticias: Boolean = false,
) {

    fun esImportante(): Boolean{
        return gradoImportancia >= 8
    }

    fun esDeMedianaImportancia(): Boolean{
        return gradoImportancia in 5..7
    }

    fun esCopada(): Boolean {
        return (esImportante() && antiguedadNoticia() < 3 && condicionEspecifica())
    }

    abstract fun condicionEspecifica(): Boolean

    fun esSensacionalista(): Boolean{
        val palabras = listOf("espectacular", "increible", "grandioso")

        val tienePalabra = palabras.any{palabra -> palabra in titulo}

        return tienePalabra && condicionEspecificaEsSensacionalista()
    }

    open fun condicionEspecificaEsSensacionalista(): Boolean = true

    fun comienzaConT(): Boolean{
        return titulo.startsWith("T", ignoreCase = true)
    }

    fun antiguedadNoticia(): Int {
        return Period.between(fechaEnQueFueEscrito, LocalDate.now()).days
    }

    abstract fun esEspecial(): Boolean

    fun cantidadPalabras(): Int {
        return desarrollo.split(" ").size
    }

    fun esDeHoy(): Boolean{
        return fechaEnQueFueEscrito == LocalDate.now()
    }
}

class ArticuloComun(codNoticia: String = "02", //hardodeado T_T
                    fechaEnQueFueEscrito: LocalDate,
                    periodistaAsociado: Periodista,
                    gradoImportancia: Int,
                    titulo: String,
                    desarrollo: String,
                    val links: MutableList<Noticia> = mutableListOf()):Noticia(codNoticia,fechaEnQueFueEscrito, periodistaAsociado, gradoImportancia, titulo, desarrollo)
{


    override fun condicionEspecifica(): Boolean {
        return (links.size >= 2)
    }

    override fun esEspecial() = false
}


class NoticiaEncubierta(codNoticia: String = "01",
                        fechaEnQueFueEscrito: LocalDate,
                        periodistaAsociado: Periodista,
                        gradoImportancia: Int,
                        titulo: String,
                        desarrollo: String,
                        val montoPagado: Double):Noticia(codNoticia,fechaEnQueFueEscrito, periodistaAsociado, gradoImportancia, titulo, desarrollo)
{
    override fun condicionEspecifica(): Boolean {
        return (montoPagado >= 2000000)

    }

    override fun esEspecial(): Boolean {
        return condicionEspecifica()
    }
}

class Reportaje(codNoticia: String = "R",
                fechaEnQueFueEscrito: LocalDate,
                periodistaAsociado: Periodista,
                gradoImportancia: Int,
                titulo: String,
                desarrollo: String,
                val entrevistado: String, //String?
                val esMusico: Boolean = false):Noticia(codNoticia, fechaEnQueFueEscrito, periodistaAsociado, gradoImportancia, titulo, desarrollo)
{
    override fun condicionEspecifica(): Boolean {
        return (entrevistado.length % 2 != 0) //que no sea par
    }

    override fun condicionEspecificaEsSensacionalista(): Boolean {
        return (entrevistado == "Dibu Martinez")
    }

    override fun esEspecial(): Boolean {
        return esMusico
    }
}













