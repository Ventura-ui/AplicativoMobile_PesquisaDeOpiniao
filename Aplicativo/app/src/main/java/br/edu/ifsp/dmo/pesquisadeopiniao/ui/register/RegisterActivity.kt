package br.edu.ifsp.dmo.pesquisadeopiniao.ui.register

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.edu.ifsp.dmo.pesquisadeopiniao.data.model.Estudante
import br.edu.ifsp.dmo.pesquisadeopiniao.databinding.ActivityRegisterBinding
import br.edu.ifsp.dmo.pesquisadeopiniao.ui.Vote.VoteActivity
import br.edu.ifsp.dmo.pesquisadeopiniao.ui.main.MainActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: RegisterViewModel
    private lateinit var voteLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        setupVoteLauncher()
        setupListeners()
    }

    private fun setupVoteLauncher() {
        voteLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            ActivityResultCallback { result ->
                if (result.resultCode == RESULT_OK) {
                    Toast.makeText(this, "Voltando ao menu!", Toast.LENGTH_SHORT).show()
                }

                val mIntent = Intent(this, MainActivity::class.java)
                startActivity(mIntent)
                finish()
            }
        )
    }

    private fun setupListeners() {
        binding.buttonVoltar.setOnClickListener {
            val mIntent = Intent(this, MainActivity::class.java)
            startActivity(mIntent)
            finish()
        }

        binding.buttonAdvance.setOnClickListener {
            registrarEstudante()
        }
    }

    private fun registrarEstudante() {
        val prontuario = binding.textProntuario.text.toString()
        val nome = binding.textNome.text.toString()

        if (prontuario.isNotEmpty() && nome.isNotEmpty()) {
            val estudante = Estudante(prontuario, nome)
            val result = viewModel.getByProntuario(prontuario)
            if (result == null) {
                val mIntent = Intent(this, VoteActivity::class.java)
                mIntent.putExtra("prontuario", estudante.prontuario)
                mIntent.putExtra("nome", estudante.nome)
                voteLauncher.launch(mIntent)
            } else {
                Toast.makeText(this, "O Usuário $prontuario já está registrado!", Toast.LENGTH_SHORT).show()
                val mIntent = Intent(this, MainActivity::class.java)
                startActivity(mIntent)
                finish()
            }
        } else {
            Toast.makeText(this, "Insira os dados corretamente!", Toast.LENGTH_SHORT).show()
        }
    }
}