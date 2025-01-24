package br.edu.ifsp.dmo.pesquisadeopiniao.ui.Vote

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.edu.ifsp.dmo.pesquisadeopiniao.data.model.Voto
import br.edu.ifsp.dmo.pesquisadeopiniao.databinding.ActivityVoteBinding
import br.edu.ifsp.dmo.pesquisadeopiniao.ui.main.MainActivity
import java.util.UUID

class VoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVoteBinding
    private lateinit var viewModel: VoteViewModel
    private var prontuario: String? = null
    private var nome: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(VoteViewModel::class.java)

        verifyExtras()
        setupListeners()
    }

    private fun setupListeners() {
        binding.buttonVoltar.setOnClickListener {
            val mIntent = Intent(this, MainActivity::class.java)
            startActivity(mIntent)
            finish()
        }

        binding.buttonVotar.setOnClickListener {
            registrarVoto()
        }

        binding.buttonVoltarMenu.setOnClickListener {
            val intent = Intent()
            setResult(RESULT_OK, intent)
            finish()
        }

        binding.buttonCopiar.setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

            val clip = ClipData.newPlainText("Código Copiado", binding.codigoVoto.text.toString())

            clipboard.setPrimaryClip(clip)

            Toast.makeText(this, "Código copiado para a área de transferência!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun verifyExtras() {
        prontuario = intent.getStringExtra("prontuario")
        nome = intent.getStringExtra("nome")
    }

    private fun registrarVoto() {
        if (prontuario != null) {
            val codigo: String = Voto.gerarCodigoDeVoto()
            val valor: Int = getEscolhaSelecionado()
            val voto = Voto(codigo, valor)

            if (viewModel.getByProntuario(prontuario!!) == null) {
                val result = viewModel.addVoto(voto.codigo, voto.valor)
                if (result != -1L) {
                    Toast.makeText(this, "Voto feito com sucesso!", Toast.LENGTH_SHORT).show()

                    val resultEstudante = viewModel.addEstudante(prontuario!!, nome!!)
                    if (resultEstudante != -1L) {
                        Toast.makeText(this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
                    }
                    modificarTela(codigo)
                } else {
                    Toast.makeText(this, "Não foi possível realizar o voto!", Toast.LENGTH_SHORT).show()
                    setResult(RESULT_CANCELED)
                    finish()
                }
            } else {
                Toast.makeText(this, "O usuário $nome já votou!", Toast.LENGTH_SHORT).show()
                setResult(RESULT_CANCELED)
                finish()
            }
        } else {
            Toast.makeText(this, "Prontuário nulo!", Toast.LENGTH_SHORT).show()
            setResult(RESULT_CANCELED)
            finish()
        }
    }

    private fun getEscolhaSelecionado(): Int {
        val opcaoSelecionada = binding.radioGroup.checkedRadioButtonId
        var timeSelecionado: String? = null

        if (opcaoSelecionada != -1) {
            val radioButtonSelecionado = findViewById<RadioButton>(opcaoSelecionada)
            timeSelecionado = radioButtonSelecionado.text.toString()
        }

        return when (timeSelecionado) {
            "Ótimo" -> 1
            "Bom" -> 2
            "Regular" -> 3
            else -> 4
        }
    }

    private fun modificarTela(codigo: String) {
        binding.mainTitle.visibility = View.GONE
        binding.question.visibility = View.GONE
        binding.radioGroup.visibility = View.GONE
        binding.buttonVotar.visibility = View.GONE
        binding.buttonVoltar.visibility = View.GONE

        binding.aviso.visibility = View.VISIBLE
        binding.codigoVoto.visibility = View.VISIBLE
        binding.buttonCopiar.visibility = View.VISIBLE
        binding.buttonVoltarMenu.visibility = View.VISIBLE
        binding.codigoVoto.setText(codigo)
    }
}