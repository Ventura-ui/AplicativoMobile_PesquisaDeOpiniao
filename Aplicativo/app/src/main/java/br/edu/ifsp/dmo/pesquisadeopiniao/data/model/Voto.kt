package br.edu.ifsp.dmo.pesquisadeopiniao.data.model

import java.util.UUID

class Voto(val codigo: String, val valor: Int) {

    companion object {
        fun gerarCodigoDeVoto(): String {
            return UUID.randomUUID().toString().replace("-", "").take(10)
        }
    }

}