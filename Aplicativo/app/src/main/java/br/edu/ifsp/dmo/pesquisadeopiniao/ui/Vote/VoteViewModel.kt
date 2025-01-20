package br.edu.ifsp.dmo.pesquisadeopiniao.ui.Vote

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.edu.ifsp.dmo.pesquisadeopiniao.data.model.Estudante
import br.edu.ifsp.dmo.pesquisadeopiniao.data.model.Voto
import br.edu.ifsp.dmo.pesquisadeopiniao.data.repository.VotoRepository

class VoteViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: VotoRepository

    private val _votos = MutableLiveData<List<Voto>>()
    val votos: LiveData<List<Voto>>
        get() = _votos

    init {
        repository = VotoRepository(application)
    }

    fun load(){
        _votos.value = repository.getAllVotos()
    }

    fun addVoto(codigo: String, valor: Int, prontuario_estudante: String) : Long{
        var result = repository.addVoto(Voto(codigo, valor, prontuario_estudante))
        load()
        return result
    }

    fun getByProntuario(prontuario_estudante: String) : Voto?{
        return repository.getVotoByProntuario(prontuario_estudante)
    }

    fun getByCodigo(codigo: String) : Voto?{
        return repository.getVotoByCodigo(codigo)
    }


}