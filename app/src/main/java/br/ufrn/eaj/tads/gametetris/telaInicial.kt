package br.ufrn.eaj.tads.gametetris

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class telaInicial : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)
    }
    fun configurar(v: View) {
        val i = Intent(this, configuracao::class.java)
        startActivity(i)
    }
    fun novoJogo(v: View) {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }
    fun continuar(v: View){

    }
}
