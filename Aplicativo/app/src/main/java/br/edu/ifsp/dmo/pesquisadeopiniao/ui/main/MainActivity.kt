package br.edu.ifsp.dmo.pesquisadeopiniao.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.dmo.pesquisadeopiniao.databinding.ActivityMainBinding
import br.edu.ifsp.dmo.pesquisadeopiniao.ui.Vote.Register.RegisterActivity
import br.edu.ifsp.dmo.pesquisadeopiniao.ui.myVote.MyVoteActivity
import br.edu.ifsp.dmo.pesquisadeopiniao.ui.result.ResultActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
    }

    private fun setupListeners(){
        binding.buttonPageVote.setOnClickListener{
            val mIntent = Intent(this, RegisterActivity::class.java)
            startActivity(mIntent)
        }

        binding.buttonPageMyvote.setOnClickListener{
            val mIntent = Intent(this, MyVoteActivity::class.java)
            startActivity(mIntent)
        }

        binding.buttonPageResults.setOnClickListener{
            val mIntent = Intent(this, ResultActivity::class.java)
            startActivity(mIntent)
        }
    }
}