package ar.edu.unsam.algo2

interface INoticiaObserver //Este observer va a trabajar con las publicaciones, no con las noticias
{
    fun realizarAccion(publicacion: Publicacion)
}

class PagarAPeriodistaObserver(): INoticiaObserver
{
    override fun realizarAccion(publicacion: Publicacion)
    {
        val umbralPalabras = 1000
        val pagoBase: Double = 50000.0
        val pagoPorSuperarUmbral: Double = 75000.0
    publicacion.listaNoticias.forEach { noticia ->
        if(noticia.cantidadPalabras() > umbralPalabras){
            noticia.periodistaAsociado.recibirPago(pagoPorSuperarUmbral)
        }else{
            noticia.periodistaAsociado.recibirPago(pagoBase)
        }
    }
    }
}

class MailObserver(val mailSender: MailSender,
                   val mailEditor: String): INoticiaObserver {

    override fun realizarAccion(publicacion: Publicacion) {
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


class EnviarAANSIObserver(val agencia: IAgenciaANSI): INoticiaObserver {

    override fun realizarAccion(publicacion: Publicacion) {
        publicacion.listaNoticias.forEach { noticia -> agencia.enviarNoticia(
            MensajeANSI(
                codNoticia = noticia.codNoticia,
                desarrollo = noticia.desarrollo.take(100), //tomo los primeros 100 caracteres
                nombrePeriodista = noticia.periodistaAsociado.nombre,
                prioridad = calcularPrioridad(noticia)
            )
        ) }
    }

    fun calcularPrioridad(noticia: Noticia): String {
        return when{
            noticia.esImportante() -> "A"
            noticia.esDeMedianaImportancia() -> "M"
            else -> "C"
        }

    }
}



