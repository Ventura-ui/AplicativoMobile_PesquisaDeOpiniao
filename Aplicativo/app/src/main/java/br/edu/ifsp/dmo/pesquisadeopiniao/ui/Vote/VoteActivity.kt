package br.edu.ifsp.dmo.pesquisadeopiniao.ui.Vote

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import br.edu.ifsp.dmo.pesquisadeopiniao.R
import br.edu.ifsp.dmo.pesquisadeopiniao.data.model.Voto
import br.edu.ifsp.dmo.pesquisadeopiniao.databinding.ActivityRegisterBinding
import br.edu.ifsp.dmo.pesquisadeopiniao.databinding.ActivityVoteBinding
import br.edu.ifsp.dmo.pesquisadeopiniao.ui.Vote.Register.RegisterViewModel

class VoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVoteBinding
    private lateinit var viewModel: VoteViewModel
    private var prontuario: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(VoteViewModel::class.java)

        verifyExtras()
        setupListeners()
    }

    private fun setupListeners(){
        binding.buttonVoltar.setOnClickListener{
            val mIntent = Intent(this, MenuActivity::class.java)
            startActivity(mIntent)
        }

        binding.buttonVotar.setOnClickListener{
            registrarVoto()
        }
    }

    private fun verifyExtras(){
        prontuario = intent.getStringExtra("prontuario")
    }



    private fun registrarVoto(){
        if(prontuario != null){

        }else{
            Toast.makeText(this, "Prontuário nulo!", Toast.LENGTH_SHORT).show()
            val mIntent = Intent(this, MenuActivity::class.java)
            startActivity(mIntent)
        }
    }

    private fun getTimeSelecionado() : Int{
        val opcaoSelecionada = binding.radioGroup.checkedRadioButtonId
        var timeSelecionado: String? = null

        if(opcaoSelecionada != -1){
            val radioButtonSelecionado = findViewById<RadioButton>(opcaoSelecionada)
            timeSelecionado = radioButtonSelecionado.text.toString()
        }

        return when (timeSelecionado) {
            "Corinthians" -> 1
            "São Paulo" -> 2
            "Palmeiras" -> 3
            else -> 4
        }
    }
}