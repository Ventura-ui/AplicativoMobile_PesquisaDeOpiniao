package br.edu.ifsp.dmo.pesquisadeopiniao.ui.Vote.Login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import br.edu.ifsp.dmo.pesquisadeopiniao.R
import br.edu.ifsp.dmo.pesquisadeopiniao.databinding.ActivityLogin2Binding
import br.edu.ifsp.dmo.pesquisadeopiniao.databinding.ActivityLoginBinding
import br.edu.ifsp.dmo.pesquisadeopiniao.databinding.ActivityRegisterBinding
import br.edu.ifsp.dmo.pesquisadeopiniao.ui.Vote.MenuActivity
import br.edu.ifsp.dmo.pesquisadeopiniao.ui.Vote.Register.RegisterViewModel
import br.edu.ifsp.dmo.pesquisadeopiniao.ui.Vote.VoteActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogin2Binding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogin2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        setupListeners()
    }

    private fun setupListeners(){
        binding.buttonVoltar.setOnClickListener{
            val mIntent = Intent(this, MenuActivity::class.java)
            startActivity(mIntent)
        }

        binding.buttonAdvance.setOnClickListener{
            loginEstudante()
        }
    }

    private fun loginEstudante(){
        val prontuario = binding.textProntuario.text.toString()

        if(prontuario.isNotEmpty()){
            val estudante = viewModel.getByProntuario(prontuario)
            if(estudante == null){
                Toast.makeText(this, "Usuário não encontrado!", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Login feito com sucesso!", Toast.LENGTH_SHORT).show()
                val mIntent = Intent(this, VoteActivity::class.java)
                mIntent.putExtra("prontuario", estudante.prontuario)
                startActivity(mIntent)
            }
        }else{
            Toast.makeText(this, "Insira o prontuário corretamente!", Toast.LENGTH_SHORT).show()
        }
    }
}