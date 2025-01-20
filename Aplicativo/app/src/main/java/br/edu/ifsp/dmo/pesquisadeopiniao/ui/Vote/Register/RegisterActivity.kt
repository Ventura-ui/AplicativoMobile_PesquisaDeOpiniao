package br.edu.ifsp.dmo.pesquisadeopiniao.ui.Vote.Register

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import br.edu.ifsp.dmo.pesquisadeopiniao.R
import br.edu.ifsp.dmo.pesquisadeopiniao.data.model.Estudante
import br.edu.ifsp.dmo.pesquisadeopiniao.databinding.ActivityMainBinding
import br.edu.ifsp.dmo.pesquisadeopiniao.databinding.ActivityRegisterBinding
import br.edu.ifsp.dmo.pesquisadeopiniao.ui.Vote.MenuActivity
import br.edu.ifsp.dmo.pesquisadeopiniao.ui.Vote.VoteActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: RegisterViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        setupListeners()
    }

    private fun setupListeners(){
        binding.buttonVoltar.setOnClickListener{
            val mIntent = Intent(this, MenuActivity::class.java)
            startActivity(mIntent)
        }

        binding.buttonAdvance.setOnClickListener{
            registrarEstudante()
        }
    }

    private fun registrarEstudante(){
        val prontuario = binding.textProntuario.text.toString()
        val nome = binding.textNome.text.toString()

        if(prontuario.isNotEmpty() && nome.isNotEmpty()){
            val estudante = Estudante(prontuario, nome)
            val result = viewModel.addEstudante(estudante.prontuario, estudante.nome)
            if(result != -1L){
                Toast.makeText(this, "Usuário registrado com sucesso!", Toast.LENGTH_SHORT).show()
                val mIntent = Intent(this, VoteActivity::class.java)
                mIntent.putExtra("prontuario", estudante.prontuario)
                startActivity(mIntent)
            }else{
                Toast.makeText(this, "Usuário já registrado!", Toast.LENGTH_SHORT).show()
                val mIntent = Intent(this, MenuActivity::class.java)
                startActivity(mIntent)
            }
        }else{
            Toast.makeText(this, "Insira os dados corretamente!", Toast.LENGTH_SHORT).show()
        }
    }
}