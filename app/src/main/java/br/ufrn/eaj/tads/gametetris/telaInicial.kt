package br.ufrn.eaj.tads .gametetris

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast


class telaInicial : AppCompatActivity() {

    var speed:Int=300

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)

        var param = intent.extras

        if (param!=null){
            speed = param.getInt("resposta")
        }

    }

    fun configurar(v: View) {
        val i = Intent(this, configuracao::class.java)
        startActivityForResult(i,1)
    }

    fun novoJogo(v: View) {
        val i = Intent(this, MainActivity::class.java)
        i.putExtra("speed", speed)
        startActivity(i)

    }

    fun continuar(v: View){
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        Toast.makeText(this, "entrou", Toast.LENGTH_SHORT).show()
//        when(requestCode){
//            1->{
//                Toast.makeText(this, "Testeou", Toast.LENGTH_SHORT).show()
//                when(resultCode){
//                   RESULT_OK-> {
//                        speed = data?.getStringExtra("resposta")?.toInt()!!
//                        Toast.makeText(this, speed, Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }
//        }
//
//    }
}
