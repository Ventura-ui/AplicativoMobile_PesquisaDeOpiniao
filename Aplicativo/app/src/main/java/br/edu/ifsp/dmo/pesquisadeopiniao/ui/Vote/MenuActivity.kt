package br.edu.ifsp.dmo.pesquisadeopiniao.ui.Vote

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.dmo.pesquisadeopiniao.R
import br.edu.ifsp.dmo.pesquisadeopiniao.databinding.ActivityMenuBinding
import br.edu.ifsp.dmo.pesquisadeopiniao.ui.Vote.Login.LoginActivity
import br.edu.ifsp.dmo.pesquisadeopiniao.ui.Vote.Register.RegisterActivity
import br.edu.ifsp.dmo.pesquisadeopiniao.ui.main.MainActivity
import br.edu.ifsp.dmo.pesquisadeopiniao.ui.myVote.MyVoteActivity
import br.edu.ifsp.dmo.pesquisadeopiniao.ui.result.ResultActivity

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
    }

    private fun setupListeners(){
        binding.buttonPageEntrar.setOnClickListener{
            val mIntent = Intent(this, LoginActivity::class.java)
            startActivity(mIntent)
        }

        binding.buttonPageRegister.setOnClickListener{
            val mIntent = Intent(this, RegisterActivity::class.java)
            startActivity(mIntent)
        }

        binding.buttonVoltar.setOnClickListener{
            val mIntent = Intent(this, MainActivity::class.java)
            startActivity(mIntent)
        }
    }
}