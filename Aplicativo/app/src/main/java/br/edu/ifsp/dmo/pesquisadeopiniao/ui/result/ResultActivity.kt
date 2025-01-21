package br.edu.ifsp.dmo.pesquisadeopiniao.ui.result

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import br.edu.ifsp.dmo.pesquisadeopiniao.R
import br.edu.ifsp.dmo.pesquisadeopiniao.data.model.Voto
import br.edu.ifsp.dmo.pesquisadeopiniao.databinding.ActivityMyVoteBinding
import br.edu.ifsp.dmo.pesquisadeopiniao.databinding.ActivityResultBinding
import br.edu.ifsp.dmo.pesquisadeopiniao.ui.main.MainActivity
import br.edu.ifsp.dmo.pesquisadeopiniao.ui.myVote.MyVoteViewModel

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding
    private lateinit var viewModel: ResultViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(ResultViewModel::class.java)

        setupResultados()
        setupListeners()
    }

    private fun setupListeners(){
        binding.buttonVoltar.setOnClickListener{
            val mIntent = Intent(this, MainActivity::class.java)
            startActivity(mIntent)
        }
    }

    private fun setupResultados(){
        val listaDeVotos: List<Voto> = viewModel.getAllVotos()

        val contagemVotos = mutableMapOf(
            1 to 0,
            2 to 0,
            3 to 0,
            4 to 0
        )

        listaDeVotos.forEach { voto ->
            if (voto.valor in contagemVotos.keys) {
                contagemVotos[voto.valor] = contagemVotos[voto.valor]!! + 1
            }
        }

        val resultadosOrdenados = contagemVotos.entries.sortedBy { it.key }

        val resultadoTexto = resultadosOrdenados.joinToString("\n\n") { (opcao, quantidade) ->
            val nome = when (opcao) {
                1 -> "Corinthians"
                2 -> "São Paulo"
                3 -> "Palmeiras"
                4 -> "Outro"
                else -> "Desconhecido"
            }
            "$opcao - $nome: $quantidade votos"
        }

        binding.votosUsuarios.setText(resultadoTexto)
    }
}