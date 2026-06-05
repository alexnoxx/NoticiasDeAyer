package ar.edu.unsam.algo2

import java.time.LocalDate

class Periodista(val nombre: String = "", //Context
                 val fechaIngreso: LocalDate,
                 val preferencias: PreferenciaPublicacion,
                 var sueldo: Int = 0)
{
    fun leGustaLaNoticiaAlPeriodista(noticia: Noticia): Boolean{
        return preferencias.preferenciasLeGusta(noticia)
    }

    fun recibirPago(pago: Int){
        sueldo = sueldo + pago
    }


}

interface PreferenciaPublicacion{
    fun preferenciasLeGusta(noticia: Noticia): Boolean //en principio devuelve boolean
}


object quiereNoticiasCopadas: PreferenciaPublicacion
{
 override fun preferenciasLeGusta(noticia: Noticia): Boolean {
    return (noticia.esCopada())
 }
}

object quierePublicarSensacionalistas: PreferenciaPublicacion
{
    override fun preferenciasLeGusta(noticia: Noticia): Boolean {
        return noticia.esSensacionalista()
    }
}

object joseDeZer: PreferenciaPublicacion
{
    override fun preferenciasLeGusta(noticia: Noticia): Boolean {
        return noticia.comienzaConT("T")
    }
}