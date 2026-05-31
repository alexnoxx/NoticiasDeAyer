package ar.edu.unsam.algo2


import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec

class AgenciaANSITest: DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest

    class AgenciaANSIStub() : IAgenciaANSI {
        val mensajesEnviados = mutableListOf<MensajeANSI>()

        override fun enviarNoticia(mensaje: MensajeANSI){
            mensajesEnviados.add(mensaje)
        }
    }

    val stubANSI = AgenciaANSIStub()



})


