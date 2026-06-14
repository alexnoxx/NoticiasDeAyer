package ar.edu.unsam.algo2

interface CriterioSeleccionNoticia{//quiero poner varios tipos de criterios y no tiene estado
    fun cumpleCondicion(noticia: Noticia): Boolean
}


object criterioPreferenciaPeriodista:CriterioSeleccionNoticia
{
    override fun cumpleCondicion(noticia: Noticia): Boolean {
        return noticia.leGustaAQuienLaEscribe() //antes: noticia.periodistaAsociado.leGustaLaNoticiaAlPeriodista(noticia)
    }
}

object criterioSensacionalista:CriterioSeleccionNoticia
{
    override fun cumpleCondicion(noticia: Noticia): Boolean{
        return noticia.esSensacionalista()
    }
}

class CriterioImportancia(var min: Int, var max: Int):CriterioSeleccionNoticia
{
    override fun cumpleCondicion(noticia: Noticia): Boolean {
        return noticia.gradoImportancia in min..max
    }
}

class CriterioCombinado(var listaCriterios: MutableList<CriterioSeleccionNoticia> = mutableListOf()): CriterioSeleccionNoticia
{
    fun agregarCriterio(criterio: CriterioSeleccionNoticia){
        listaCriterios.add(criterio)
    }

    fun eliminarCriterio(criterio: CriterioSeleccionNoticia)
    {
        listaCriterios.remove(criterio)
    }

    override fun cumpleCondicion(noticia: Noticia): Boolean
    {
        return listaCriterios.all{condicion -> condicion.cumpleCondicion(noticia)}
    }
}