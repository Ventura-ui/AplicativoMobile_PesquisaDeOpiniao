package br.edu.ifsp.dmo.pesquisadeopiniao.ui.result

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.edu.ifsp.dmo.pesquisadeopiniao.data.model.Voto
import br.edu.ifsp.dmo.pesquisadeopiniao.data.repository.EstudanteRepository
import br.edu.ifsp.dmo.pesquisadeopiniao.data.repository.VotoRepository

class ResultViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: VotoRepository

    private val _votos = MutableLiveData<List<Voto>>()
    val votos: LiveData<List<Voto>>
        get() = _votos

    init {
        repository = VotoRepository(application)
    }

    fun getAllVotos() : List<Voto>{
        return repository.getAllVotos()
    }

}