package br.edu.ifsp.dmo.pesquisadeopiniao.data.repository

import android.content.Context
import br.edu.ifsp.dmo.pesquisadeopiniao.data.database.DatabaseHelper
import br.edu.ifsp.dmo.pesquisadeopiniao.data.database.EstudanteDao
import br.edu.ifsp.dmo.pesquisadeopiniao.data.database.VotoDao
import br.edu.ifsp.dmo.pesquisadeopiniao.data.model.Estudante

class EstudanteRepository(context: Context) {

    private val dbHelper = DatabaseHelper(context)
    private val dao = EstudanteDao(dbHelper)

    fun addEstudante(estudante: Estudante) : Long = dao.inserirEstudante(estudante)

    fun getByProntuario(prontuario: String): Estudante? = dao.getByProntuario(prontuario)

    fun getAllEstudantes(): List<Estudante> = dao.getAllEstudantes()

}