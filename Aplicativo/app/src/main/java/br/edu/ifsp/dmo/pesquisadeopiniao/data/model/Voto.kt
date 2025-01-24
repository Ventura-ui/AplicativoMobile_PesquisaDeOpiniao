package br.edu.ifsp.dmo.pesquisadeopiniao.data.model

import java.util.UUID

class Voto(val codigo: String, val valor: Int) {

    companion object {
        fun gerarCodigoDeVoto(): String {
            return UUID.randomUUID().toString().replace("-", "").take(10)
        }

        fun getOpcaoVoto(opcao: Int) : String{
            return when (opcao) {
                1 -> "Ótimo"
                2 -> "Bom"
                3 -> "Regular"
                else -> "Ruim"
            }
        }
    }

}