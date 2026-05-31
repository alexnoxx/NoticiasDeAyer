package ar.edu.unsam.algo2

interface IAgenciaANSI{ //Esta interface me va a conectar con el Stub
    fun enviarNoticia(mensaje: MensajeANSI)
}

data class MensajeANSI (val codNoticia: String,
                         val desarrollo: String,
                         val nombrePeriodista: String,
                         val prioridad: String)