package ar.edu.unsam.algo2

interface IAgenciaANSI{ //Esta interface me va a conectar con el Stub. Eso era antes, desistire de usar Stub

    fun enviarReporte(reporteANSI: MensajeANSI)
}

data class MensajeANSI (var codNoticia: String,
                         var desarrolloNoticia: String,
                         var nombrePeriodista: String,
                         var prioridad: String)