package br.ufrn.eaj.tads.gametetris

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_configuracao.*
import kotlinx.android.synthetic.main.activity_tela_inicial.*

class configuracao : AppCompatActivity() {

    var speed:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuracao)

    }

    fun altVelocidade(v: View){

        when (grupoBtn.checkedRadioButtonId){
            facil.id->{
                //Toast.makeText(this, "Facil", Toast.LENGTH_SHORT).show()
                speed = 300
            }
            medio.id->{
                //Toast.makeText(this, "Medio", Toast.LENGTH_SHORT).show()
                speed = 150
                }
            dificil.id->{
                //Toast.makeText(this, "Dificil", Toast.LENGTH_SHORT).show()
                speed = 1000
            }
        }

        var i = Intent()
        i.putExtra("resposta", speed)
        //startActivity(i)
        setResult(Activity.RESULT_OK, i)
        finish()
    }
}
