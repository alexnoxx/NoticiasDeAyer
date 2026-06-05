package ar.edu.unsam.algo2

import java.time.LocalDate

class Periodista(val nombre: String, //Context
                 val fechaIngreso: LocalDate,
                 val preferencias: IPreferenciasGrillo,
                 var sueldo: Double = 0.0)
{
    fun leGustaLaNoticiaAlPeriodista(noticia: Noticia): Boolean{
        return preferencias.preferenciasLeGusta(noticia)
    }

    fun recibirPago(pago: Double): Double{
        sueldo = sueldo + pago
        return sueldo
    }


}

interface IPreferenciasGrillo{//como el periodista del Eternauta
    fun preferenciasLeGusta(noticia: Noticia): Boolean //en principio devuelve boolean
}


object quiereNoticiasCopadas: IPreferenciasGrillo
{
 override fun preferenciasLeGusta(noticia: Noticia): Boolean {
    return (noticia.esCopada())
 }
}

class quierePublicarSensacionalistas: IPreferenciasGrillo
{
    override fun preferenciasLeGusta(noticia: Noticia): Boolean {
        return noticia.esSensacionalista()
    }
}

object joseDeZer: IPreferenciasGrillo
{
    override fun preferenciasLeGusta(noticia: Noticia): Boolean {
        return noticia.comienzaConT()
    }
}