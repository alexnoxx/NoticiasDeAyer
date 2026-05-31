package ar.edu.unsam.algo2

import java.time.LocalDate

data class Publicacion(val fecha: LocalDate,
                       val listaNoticias: MutableList<Noticia>)