package ar.edu.unsam.algo2

interface IAgenciaANSI{ //Esta interface me va a conectar con el Stub
    fun enviarNoticia(mensaje: MensajeANSI)
}

data class MensajeANSI (var codNoticia: String,
                         var desarrollo: String,
                         var nombrePeriodista: String,
                         var prioridad: String)