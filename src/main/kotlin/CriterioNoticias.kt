package ar.edu.unsam.algo2

interface CriterioNoticia{
    fun cumpleCondicion(noticia: Noticia): Boolean
}


object criterioPreferenciaPeriodista:CriterioNoticia
{
    override fun cumpleCondicion(noticia: Noticia): Boolean {
        return noticia.periodistaAsociado.leGustaLaNoticiaAlPeriodista(noticia)
    }
}

object criterioSensacionalista:CriterioNoticia
{
    override fun cumpleCondicion(noticia: Noticia): Boolean{
        return noticia.esSensacionalista()
    }
}

class CriterioImportancia(val min: Int, val max: Int):CriterioNoticia
{
    override fun cumpleCondicion(noticia: Noticia): Boolean {
        return noticia.gradoImportancia in min..max
    }
}

class CriterioCombinado(var listaCriterios: MutableList<CriterioNoticia> = mutableListOf()): CriterioNoticia
{
    fun agregarCriterio(criterio: CriterioNoticia){
        listaCriterios.add(criterio)
    }

    fun eliminarCriterio(criterio: CriterioNoticia)
    {
        listaCriterios.remove(criterio)
    }

    override fun cumpleCondicion(noticia: Noticia): Boolean
    {
        return listaCriterios.all{condicion -> condicion.cumpleCondicion(noticia)}
    }
}