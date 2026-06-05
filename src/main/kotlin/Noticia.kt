package ar.edu.unsam.algo2

import java.time.LocalDate
import java.time.Period

abstract class Noticia(
                        val fechaEnQueFueEscrito: LocalDate = LocalDate.now(),
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

    fun comienzaConT(letra: String): Boolean{
        return titulo.startsWith(letra, ignoreCase = true)
    }

    fun antiguedadNoticia(): Int {
        return Period.between(fechaEnQueFueEscrito, LocalDate.now()).days
    }

    abstract fun esEspecial(): Boolean
/* metodo que tenia antes
    fun cantidadPalabras(): Int {
        return desarrollo.split(" ").size
    }
*/
    fun esDeHoy(): Boolean{
        return fechaEnQueFueEscrito == LocalDate.now()
    }

    fun leGustaAQuienLaEscribe(): Boolean = periodistaAsociado.leGustaLaNoticiaAlPeriodista(this)

    fun pagarAPeriodistaAsociado(){
        periodistaAsociado.recibirPago(valor())
    }

    fun valor(): Int = if(cantidadPalabras() > 1000) 75 else 50

    fun cantidadPalabras() = desarrollo.words().size

    abstract fun codigoNoticia(): String

    fun prioridad(): String = "" //aca se puede usar un when para clasificar los diferentes casos del ultimo observer que nos piden

    fun generarNoticiaANSI() = MensajeANSI(codigoNoticia(), desarrollo, periodistaAsociado.nombre, prioridad())
    }
/*
data class MensajeANSI (var codNoticia: String,
                         var desarrolloNoticia: String,
                         var nombrePeriodista: String,
                         var prioridad: String)
 */



fun String.words() = this.split(" ") //otro extension method mas
                                                // funcion de primer orden

class ArticuloComun(fechaEnQueFueEscrito: LocalDate,
                    periodistaAsociado: Periodista,
                    gradoImportancia: Int,
                    titulo: String,
                    desarrollo: String,
                    val links: MutableList<Noticia> = mutableListOf()):Noticia(fechaEnQueFueEscrito, periodistaAsociado, gradoImportancia, titulo, desarrollo)
{

    override fun codigoNoticia(): String = "02"

    override fun condicionEspecifica(): Boolean {
        return (links.size >= 2)
    }

    override fun esEspecial() = false
}


class NoticiaEncubierta(
                        fechaEnQueFueEscrito: LocalDate,
                        periodistaAsociado: Periodista,
                        gradoImportancia: Int,
                        titulo: String,
                        desarrollo: String,
                        val montoPagado: Double):Noticia(fechaEnQueFueEscrito, periodistaAsociado, gradoImportancia, titulo, desarrollo)
{
    override fun condicionEspecifica(): Boolean {
        return (montoPagado >= 2000000)

    }

    override fun esEspecial(): Boolean {
        return condicionEspecifica()
    }

    override fun codigoNoticia(): String {
        return "01"
    }
}

class Reportaje(
                fechaEnQueFueEscrito: LocalDate,
                periodistaAsociado: Periodista,
                gradoImportancia: Int,
                titulo: String,
                desarrollo: String,
                val entrevistado: Entrevistado, //el invitado te dice si se dedica a la musica. Esta informacion no depende del reportaje
                val esMusico: Boolean = false):Noticia( fechaEnQueFueEscrito, periodistaAsociado, gradoImportancia, titulo, desarrollo)
{
    override fun condicionEspecifica(): Boolean {
        return (entrevistado.tieneUnNombrePar()) //que no sea par
    }

    override fun condicionEspecificaEsSensacionalista(): Boolean {
        return (entrevistado.esEntrevistaA("Dibu Martinez"))
    }

    override fun esEspecial(): Boolean {
        return esMusico
    }

    override fun codigoNoticia(): String {
        return "R"
    }
}


data class Entrevistado(val nombre: String,
                        val seDedicaALaMusica: Boolean)
{
    fun tieneUnNombrePar(): Boolean = nombre.odd()
    fun esEntrevistaA(entrevistado: String) = nombre == entrevistado
}

fun String.odd() = this.length % 2 != 0 //extension method









