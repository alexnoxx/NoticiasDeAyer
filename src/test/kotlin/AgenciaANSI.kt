package ar.edu.unsam.algo2


import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec

class AgenciaANSITest: DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest

    //describe(""){
    val stubANSI = StubAgenciaANSI()



})

class StubAgenciaANSI() : IAgenciaANSI {
    val mensajesEnviados = mutableListOf<MensajeANSI>()

    override fun enviarNoticia(mensaje: MensajeANSI){
        mensajesEnviados.add(mensaje)
    }
}
