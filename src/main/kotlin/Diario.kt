package ar.edu.unsam.algo2

import java.time.LocalDate

//antes Administrador(). No usa "Administrador" porque es un rol, hablando semanticamente.
class Diario(val criterioSeleccionNoticia: CriterioSeleccionNoticia, //recibe/inyecta un criterio simple o combinado
             var listaNoticias: MutableList<Noticia> = mutableListOf<Noticia>()) //Context
{
/*
    val listaObserver: MutableList<INoticiaObserver> = mutableListOf<INoticiaObserver>()

    fun agregarObserverALista(observer: INoticiaObserver) { //agrego observers a la lista
        listaObserver.add(observer)
    }

    fun eliminarObserverALista(observer: INoticiaObserver) {
        listaObserver.remove(observer)
    }
*/

    //Moderacion de publicacion

    fun generarPublicacion(/*noticiasARevisar: MutableList<Noticia>*/): Publicacion {//Caso de uso asincronico. Flujo de cosas que ocurren.
        val noticiasQueCumplen: List<Noticia> = listaNoticias.filter{noticia -> this.sePuedePublicar(noticia)} //this = diario
        val fechaDeManiana: LocalDate = LocalDate.now().plusDays(1)

        return Publicacion(fechaDeManiana, noticiasQueCumplen.toMutableList())
        //listaNoticiasConfirmadas = noticiasARevisar.filter{noticia -> noticiasAPublicar(noticia) }.toMutableList() //convierte a lista mutable ya que filter devuelve lista inmutable
    }

    fun sePuedePublicar(noticia: Noticia): Boolean {
        return criterioSeleccionNoticia.cumpleCondicion(noticia)
    }
/*
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
*/
}