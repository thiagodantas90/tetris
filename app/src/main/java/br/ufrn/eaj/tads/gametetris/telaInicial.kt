package br.ufrn.eaj.tads .gametetris

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast


class telaInicial : AppCompatActivity() {

    var speed:Int=500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)

    }

    fun configurar(v: View) {
        val i = Intent(this, configuracao::class.java)
        startActivityForResult(i,1)
    }

    fun novoJogo(v: View) {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }

    fun continuar(v: View){
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }

}
