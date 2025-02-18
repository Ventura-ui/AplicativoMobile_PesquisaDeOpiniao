package br.edu.ifsp.dmo.pesquisadeopiniao.ui.myVote

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.edu.ifsp.dmo.pesquisadeopiniao.data.model.Estudante
import br.edu.ifsp.dmo.pesquisadeopiniao.data.model.Voto
import br.edu.ifsp.dmo.pesquisadeopiniao.data.repository.EstudanteRepository
import br.edu.ifsp.dmo.pesquisadeopiniao.data.repository.VotoRepository

class MyVoteViewModel(application: Application) : AndroidViewModel(application) {

    private var votoRepository: VotoRepository

    private val _votos = MutableLiveData<List<Voto>>()
    val votos: LiveData<List<Voto>>
        get() = _votos

    init {
        votoRepository = VotoRepository(application)
    }

    fun getByCodigo(codigo: String) : Voto?{
        return votoRepository.getVotoByCodigo(codigo)
    }
}