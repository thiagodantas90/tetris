package br.ufrn.eaj.tads.gametetris

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_configuracao.*
import kotlinx.android.synthetic.main.activity_tela_inicial.*

class configuracao : AppCompatActivity() {

    var PREFS:String=toString()
    var speed:Long=0

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
                speed = 75
            }
        }
        finish()

    }

    override fun onStop() {
        super.onStop()

        val setting = getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        with((setting.edit())){
            putLong("Recorde", speed)
            //putInt("pontos", null)
        }
    }
}
