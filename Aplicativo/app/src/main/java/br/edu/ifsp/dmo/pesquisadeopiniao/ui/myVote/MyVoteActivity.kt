package br.edu.ifsp.dmo.pesquisadeopiniao.ui.myVote

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import br.edu.ifsp.dmo.pesquisadeopiniao.R
import br.edu.ifsp.dmo.pesquisadeopiniao.data.model.Voto
import br.edu.ifsp.dmo.pesquisadeopiniao.databinding.ActivityMyVoteBinding
import br.edu.ifsp.dmo.pesquisadeopiniao.databinding.ActivityVoteBinding
import br.edu.ifsp.dmo.pesquisadeopiniao.ui.Vote.MenuActivity
import br.edu.ifsp.dmo.pesquisadeopiniao.ui.Vote.VoteViewModel
import br.edu.ifsp.dmo.pesquisadeopiniao.ui.main.MainActivity

class MyVoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyVoteBinding
    private lateinit var viewModel: MyVoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyVoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MyVoteViewModel::class.java)

        setupListeners()
    }

    private fun setupListeners(){
        binding.buttonVoltar.setOnClickListener{
            val mIntent = Intent(this, MainActivity::class.java)
            startActivity(mIntent)
        }

        binding.buttonSearch.setOnClickListener{
            fazerPesquisa()
        }
    }

    private fun getOpcaoVoto(opcao: Int) : String{
        return when (opcao) {
            1 -> "Corinthians"
            2 -> "São Paulo"
            3 -> "Palmeiras"
            else -> "Outro"
        }
    }

    private fun fazerPesquisa(){
        var codigoText: String = binding.textCodigo.text.toString()

        if(codigoText.isNotEmpty()){
            if(viewModel.getByCodigo(codigoText) != null){
                var voto = viewModel.getByCodigo(codigoText)!!
                var estudante = viewModel.getByProntuario(voto.codigo_estudante)
                if(estudante != null){
                    var textVoto: String = "${estudante.nome} - (${estudante.prontuario})\n" + "Voto: ${getOpcaoVoto(voto.valor)}"
                    binding.votoUsuario.visibility = View.VISIBLE
                    binding.votoUsuario.setText(textVoto)
                    binding.textCodigo.setText("")
                }
            }else{
                Toast.makeText(this, "Código não encontrado!", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this, "Código vazio!", Toast.LENGTH_SHORT).show()
        }
    }
}