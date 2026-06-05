package ar.edu.unsam.algo2

interface PublicacionObserver //Este observer va a trabajar con las publicaciones, no con las noticias
{                           //No hay validaciones
    fun notificarPublicacionConfirmada(publicacion: Publicacion) {
        //publicacion.listaNoticias.forEach{noticia -> notificarNoticiaPublicada(noticia)}
        noticiasQueMeInteresan(publicacion).forEach{noticia -> notificarNoticiaPublicada(noticia)}  //hago esto para no repetir el foreach en cada observer. Basicamente es un delegated method.
    }


    fun noticiasQueMeInteresan(publicacion: Publicacion) = publicacion.listaNoticias

    fun notificarNoticiaPublicada(noticia: Noticia)


}

class PagarAPeriodistaObserver(): PublicacionObserver
{
    override fun notificarNoticiaPublicada(noticia: Noticia)
    {//el observer no debe conocer la logica de negocio de la noticia
        //el observer sabe que tiene dar la notificacion de pagar la noticia
        /*
        val umbralPalabras = 1000
        val pagoBase: Double = 50000.0
        val pagoPorSuperarUmbral: Double = 75000.0
         */
        //publicacion.listaNoticias.forEach { noticia -> noticia.pagarAPeriodistaAsociado()
        noticia.pagarAPeriodistaAsociado()  //NOTA: Utilizo delegated method. Deberia reemplazar el foreach anterior por: noticia.pagarAPeriodista()
        /*if(noticia.cantidadPalabras() > umbralPalabras){
            noticia.periodistaAsociado.recibirPago(pagoPorSuperarUmbral)
        }else{
            noticia.periodistaAsociado.recibirPago(pagoBase)
        }

    }
    */
    }
}

class MailObserver(val mailSender: MailSender,
                   val mailEditor: String): PublicacionObserver {

    override fun noticiasQueMeInteresan(publicacion: Publicacion): MutableList<Noticia> {
        return publicacion.noticias.filter{} //falta completar
    }                                       // esta es una opcion para no tener que repetir codigo. Para que no tenga que hacer el foreach en los tres observers
                                            // tambien se puede mejorar no podiendo publicacion.noticias
                                            //va haber que refactorizar todo el codigo que sigue

    override fun notificarPublicacionConfirmada(publicacion: Publicacion) {
        publicacion.listaNoticias.forEach { noticia ->
            if (noticia.esEspecial()) {
                mailSender.sendMail(
                    Mail(
                        from = "root@noticiasvip.com",
                        to = mailEditor,
                        subject = "noticias-vip",
                        body = "Notificacion especial: ${noticia.titulo}"
                    )
                )
            }
        }
    }
}


class EnviarAANSIObserver(var agencia: IAgenciaANSI): PublicacionObserver {
    /*
        override fun notificarPublicacionConfirmada(noticia: Noticia) {

            publicacion.listaNoticias.forEach { noticia -> agencia.enviarNoticia( //no esta bueno hacer publicacion.listaNoticias.forEach {
                MensajeANSI(
                    codNoticia = noticia.codNoticia,
                    desarrollo = noticia.desarrollo.take(100), //tomo los primeros 100 caracteres
                    nombrePeriodista = noticia.periodistaAsociado.nombre,
                    prioridad = calcularPrioridad(noticia)
                )
            ) }
            */
    override fun notificarNoticiaPublicada(noticia: Noticia) {
        agencia.enviarReporte(noticia.generarNoticiaANSI()) //Envia un reporte por cada noticia
    }

    }
/*
    fun calcularPrioridad(noticia: Noticia): String {
        return when{
            noticia.esImportante() -> "A"
            noticia.esDeMedianaImportancia() -> "M"
            else -> "C"
        }



    }

}
*/


