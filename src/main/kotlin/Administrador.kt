package ar.edu.unsam.algo2

import java.time.LocalDate


class Diario(val criterioNoticia: CriterioNoticia, //recibe/inyecta un criterio simple o combinado
                    var listaNoticiasConfirmadas: MutableList<Noticia> = mutableListOf()) //Context
{

    val listaObserver: MutableList<INoticiaObserver> = mutableListOf<INoticiaObserver>()

    fun agregarObserverALista(observer: INoticiaObserver) { //agrego observers a la lista
        listaObserver.add(observer)
    }

    fun eliminarObserverALista(observer: INoticiaObserver) {
        listaObserver.remove(observer)
    }


    //Moderacion de publicacion

    fun generarPublicacion(noticiasARevisar: MutableList<Noticia>) {
    listaNoticiasConfirmadas = noticiasARevisar.filter{noticia -> noticiasAPublicar(noticia) }.toMutableList() //convierte a lista mutable ya que filter devuelve lista inmutable
    }

    fun noticiasAPublicar(noticia: Noticia): Boolean {
        return criterioNoticia.cumpleCondicion(noticia) && noticia.esDeHoy()
    }

    fun agregarNoticiaConfirmada(noticia:Noticia){
        listaNoticiasConfirmadas.add(noticia)
    }

    fun eliminarNoticiaConfirmada(noticia:Noticia){
        listaNoticiasConfirmadas.remove(noticia)
    }

    fun lanzarPublicacion(){
        val publicacion = Publicacion(LocalDate.now(), listaNoticiasConfirmadas)

        listaObserver.forEach{observer -> observer.realizarAccion(publicacion)} //notifico a cada observer que ya ha ocurrido el lanzamiento
    }

}