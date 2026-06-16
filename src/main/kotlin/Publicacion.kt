package ar.edu.unsam.algo2

import java.time.LocalDate

//La idea es generar una publicacion en estado Borrador
// Hay que partir todo lo que esta en el punto 3) en varios metodos ya que se trata de un caso de uso asincronico.
data class Publicacion(val fecha: LocalDate,
                       val listaNoticias: MutableList<Noticia>) {//lista de noticias que van a formar parte de la publicacion
    var estado: EstadoDePublicacion = EstadoDePublicacion.PENDIENTE
    val publicacionObservers = listOf<PublicacionObserver>()

    fun agregarNoticia(noticia: Noticia) {//comportamiento del caso de uso asincronico el de agregar y eliminar noticias
        listaNoticias.add(noticia) //agrego noticias a la lista
    }

    fun elimiarNoticia(noticia: Noticia) {
        listaNoticias.remove(noticia)
    }

    fun confirmarPublicacion() {// actualiza la bandera
        estado = EstadoDePublicacion.PENDIENTE

        publicacionObservers.forEach { it.notificarPublicacionConfirmada(this)  }//aqui van los foreach. Le voy a pedir a cada observer ...
    }                                                                           //le paso como parametro la Publicacion
}

enum class EstadoDePublicacion{
    PENDIENTE, CONFIRMADA
}