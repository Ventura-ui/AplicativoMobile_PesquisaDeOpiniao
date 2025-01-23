package br.edu.ifsp.dmo.pesquisadeopiniao.ui.Vote.Register

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.edu.ifsp.dmo.pesquisadeopiniao.data.model.Estudante
import br.edu.ifsp.dmo.pesquisadeopiniao.data.repository.EstudanteRepository

class RegisterViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: EstudanteRepository

    private val _estudantes = MutableLiveData<List<Estudante>>()
    val estudantes: LiveData<List<Estudante>>
        get() = _estudantes

    init {
        repository = EstudanteRepository(application)
    }

    fun load(){
        _estudantes.value = repository.getAllEstudantes()
    }

    fun addEstudante(prontuario: String, nome: String) : Long{
        var result = repository.addEstudante(Estudante(prontuario, nome))
        load()
        return result
    }

    fun getByProntuario(prontuario: String) : Estudante?{
        return repository.getByProntuario(prontuario)
    }
}