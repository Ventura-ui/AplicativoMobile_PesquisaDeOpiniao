package br.edu.ifsp.dmo.pesquisadeopiniao.data.repository

import android.content.Context
import br.edu.ifsp.dmo.pesquisadeopiniao.data.database.DatabaseHelper
import br.edu.ifsp.dmo.pesquisadeopiniao.data.database.VotoDao
import br.edu.ifsp.dmo.pesquisadeopiniao.data.model.Voto

class VotoRepository(context: Context) {

    private val dbHelper = DatabaseHelper(context)
    private val dao = VotoDao(dbHelper)

    fun addVoto(voto: Voto) : Long = dao.inserirVoto(voto)

    fun getAllVotos(): List<Voto> = dao.getAllVotos()

    fun getVotoByCodigo(codigo: String): Voto? = dao.getVotoByCodigo(codigo)

    fun getVotoByProntuario(prontuario: String): Voto? = dao.getVotoByProntuario(prontuario)

}