package br.edu.ifsp.dmo.pesquisadeopiniao.ui.Vote

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.edu.ifsp.dmo.pesquisadeopiniao.data.model.Estudante
import br.edu.ifsp.dmo.pesquisadeopiniao.data.model.Voto
import br.edu.ifsp.dmo.pesquisadeopiniao.data.repository.EstudanteRepository
import br.edu.ifsp.dmo.pesquisadeopiniao.data.repository.VotoRepository

class VoteViewModel(application: Application) : AndroidViewModel(application) {

    private var votoRepository: VotoRepository
    private var estudanteRepository: EstudanteRepository

    private val _votos = MutableLiveData<List<Voto>>()
    val votos: LiveData<List<Voto>>
        get() = _votos

    private val _estudantes = MutableLiveData<List<Estudante>>()
    val estudantes: LiveData<List<Estudante>>
        get() = _estudantes

    init {
        votoRepository = VotoRepository(application)
        estudanteRepository = EstudanteRepository(application)
    }

    fun load(){
        _votos.value = votoRepository.getAllVotos()
        _estudantes.value = estudanteRepository.getAllEstudantes()
    }

    fun addVoto(codigo: String, valor: Int) : Long{
        var result = votoRepository.addVoto(Voto(codigo, valor))
        load()
        return result
    }

    fun addEstudante(prontuario: String, nome: String) : Long{
        var result = estudanteRepository.addEstudante(Estudante(prontuario, nome))
        load()
        return result
    }

    fun getByProntuario(prontuario: String) : Estudante?{
        return estudanteRepository.getByProntuario(prontuario)
    }

}