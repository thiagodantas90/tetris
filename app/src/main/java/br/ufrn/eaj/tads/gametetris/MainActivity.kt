package br.ufrn.eaj.tads.gametetris

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import android.view.LayoutInflater
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.PersistableBundle
import android.view.View
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    var PREFS = toString()
    var recorde:Int=0



    val LINHA = 36
    val COLUNA = 26
    var running = true
    var speed:Long = 300
    var peca = 1
    var cont = 0

    var pt = Ponto(0,15)

    inner class Ponto(var x:Int,var y:Int){
        fun moveDown(){
            x++
        }
        fun moveRight(){
            y++
        }
        fun moveLeft(){
            y--
        }

    }


    //val board = Array(LINHA, { IntArray(COLUNA) })

    var board = Array(LINHA) {
        Array(COLUNA){0}
    }

    var boardView = Array(LINHA){
        arrayOfNulls<ImageView>(COLUNA)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var param = intent.extras
        if (param != null) {
            speed = param.getInt("speed").toLong()
        }

        //recuperando o recorde
        val settings = getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        recorde = settings.getInt("Recorde",0)

        gridboard.rowCount = LINHA
        gridboard.columnCount = COLUNA

        val inflater = LayoutInflater.from(this)

        for (i in 0 until LINHA) {
            for (j in 0 until COLUNA) {
                boardView[i][j] = inflater.inflate(R.layout.inflate_image_view, gridboard, false) as ImageView
                gridboard.addView( boardView[i][j])
            }
        }

        gameRun()
    }


    fun direta(v:View){
        pt.moveRight()
    }
    fun esquerda(v:View){
        pt.moveLeft()
    }
    //gira a peça
    fun girar(v:View){
       cont++
    }
    fun cair(v:View){
        speed = 100
    }
    fun pausa(v:View){

    }


    fun gameRun(){

        Thread{
            //peca = Random.nextInt(1, 7)
            while(running){

                Thread.sleep(speed)
                runOnUiThread{
                    //limpa tela
                    for (i in 0 until LINHA) {
                        for (j in 0 until COLUNA) {
                            boardView[i][j]!!.setImageResource(R.drawable.black)
                        }
                    }
                    //move peça atual
                    pt.moveDown()
                    //print peça
                    if(peca == 1){
                        if(cont == 0){
                            try {

                                boardView[pt.x][pt.y]!!.setImageResource(R.drawable.white)
                                boardView[pt.x-1][pt.y]!!.setImageResource(R.drawable.white)
                                boardView[pt.x][pt.y+1]!!.setImageResource(R.drawable.white)
                                boardView[pt.x+1][pt.y]!!.setImageResource(R.drawable.white)


                            }catch (e:ArrayIndexOutOfBoundsException ) {
                                //se a peça passou das bordas eu vou parar o jogo

                            }
                        }else if (cont == 1){
                            try {
                                boardView[pt.x][pt.y]!!.setImageResource(R.drawable.white)
                                boardView[pt.x][pt.y-1]!!.setImageResource(R.drawable.white)
                                boardView[pt.x][pt.y+1]!!.setImageResource(R.drawable.white)
                                boardView[pt.x+1][pt.y]!!.setImageResource(R.drawable.white)
                            }catch (e:ArrayIndexOutOfBoundsException ) {
                                //se a peça passou das bordas eu vou parar o jogo
                                running = true
                            }
                        }else if(cont == 2){
                            try {
                                boardView[pt.x][pt.y]!!.setImageResource(R.drawable.white)
                                boardView[pt.x][pt.y-1]!!.setImageResource(R.drawable.white)
                                boardView[pt.x-1][pt.y]!!.setImageResource(R.drawable.white)
                                boardView[pt.x+1][pt.y]!!.setImageResource(R.drawable.white)
                            }catch (e:ArrayIndexOutOfBoundsException ) {
                                //se a peça passou das bordas eu vou parar o jogo
                                running = true
                            }
                        }else if(cont == 3){
                            try {
                                boardView[pt.x][pt.y]!!.setImageResource(R.drawable.white)
                                boardView[pt.x][pt.y-1]!!.setImageResource(R.drawable.white)
                                boardView[pt.x-1][pt.y]!!.setImageResource(R.drawable.white)
                                boardView[pt.x][pt.y+1]!!.setImageResource(R.drawable.white)
                            }catch (e:ArrayIndexOutOfBoundsException ) {
                                //se a peça passou das bordas eu vou parar o jogo
                                running = true
                            }
                        }else if(cont == 4){
                            cont = 0
                        }
                    }else if(peca == 2){
                        try {
                            boardView[pt.x][pt.y]!!.setImageResource(R.drawable.white)
                            boardView[pt.x+1][pt.y]!!.setImageResource(R.drawable.white)
                            boardView[pt.x][pt.y-1]!!.setImageResource(R.drawable.white)
                            boardView[pt.x+1][pt.y-1]!!.setImageResource(R.drawable.white)
                        }catch (e:ArrayIndexOutOfBoundsException ) {
                            //se a peça passou das bordas eu vou parar o jogo
                            running = true
                        }

                    }else if(peca == 3){
                        if(cont == 0){
                            try {
                                boardView[pt.x][pt.y]!!.setImageResource(R.drawable.white)
                                boardView[pt.x+1][pt.y]!!.setImageResource(R.drawable.white)
                                boardView[pt.x][pt.y-1]!!.setImageResource(R.drawable.white)
                                boardView[pt.x-1][pt.y-1]!!.setImageResource(R.drawable.white)
                            }catch (e:ArrayIndexOutOfBoundsException ) {
                                //se a peça passou das bordas eu vou parar o jogo
                                running = true
                            }
                        }else if(cont == 1){
                            try {
                                boardView[pt.x][pt.y]!!.setImageResource(R.drawable.white)
                                boardView[pt.x+1][pt.y]!!.setImageResource(R.drawable.white)
                                boardView[pt.x][pt.y+1]!!.setImageResource(R.drawable.white)
                                boardView[pt.x+1][pt.y-1]!!.setImageResource(R.drawable.white)
                            }catch (e:ArrayIndexOutOfBoundsException ) {
                                //se a peça passou das bordas eu vou parar o jogo
                                running = true
                            }
                        }
                        else if(cont == 2){
                            cont = 0
                        }

                    }else if(peca == 4){
                        if(cont == 0){
                            try {
                                boardView[pt.x][pt.y+1]!!.setImageResource(R.drawable.white)
                                boardView[pt.x][pt.y-1]!!.setImageResource(R.drawable.white)
                                boardView[pt.x][pt.y-2]!!.setImageResource(R.drawable.white)
                                boardView[pt.x][pt.y]!!.setImageResource(R.drawable.white)
                            }catch (e:ArrayIndexOutOfBoundsException ) {
                                //se a peça passou das bordas eu vou parar o jogo
                                running = true
                            }
                        }else if(cont == 1){
                            try {
                                boardView[pt.x+1][pt.y]!!.setImageResource(R.drawable.white)
                                boardView[pt.x-1][pt.y]!!.setImageResource(R.drawable.white)
                                boardView[pt.x-2][pt.y]!!.setImageResource(R.drawable.white)
                                boardView[pt.x][pt.y]!!.setImageResource(R.drawable.white)
                            }catch (e:ArrayIndexOutOfBoundsException ) {
                                //se a peça passou das bordas eu vou parar o jogo
                                running = true
                            }
                        }else if(cont == 2){
                            cont = 0
                        }

                    }else if(peca == 5){
                        if(cont == 0){
                            try {
                                boardView[pt.x][pt.y]!!.setImageResource(R.drawable.white)
                                boardView[pt.x][pt.y+1]!!.setImageResource(R.drawable.white)
                                boardView[pt.x][pt.y-1]!!.setImageResource(R.drawable.white)
                                boardView[pt.x+1][pt.y-1]!!.setImageResource(R.drawable.white)
                            }catch (e:ArrayIndexOutOfBoundsException ) {
                                //se a peça passou das bordas eu vou parar o jogo
                                running = true
                            }
                        }else if(cont == 1){
                            try {
                                boardView[pt.x][pt.y]!!.setImageResource(R.drawable.white)
                                boardView[pt.x+1][pt.y]!!.setImageResource(R.drawable.white)
                                boardView[pt.x-1][pt.y]!!.setImageResource(R.drawable.white)
                                boardView[pt.x-1][pt.y-1]!!.setImageResource(R.drawable.white)
                            }catch (e:ArrayIndexOutOfBoundsException ) {
                                //se a peça passou das bordas eu vou parar o jogo
                                running = true
                            }
                        }else if(cont == 2){
                            try {
                                boardView[pt.x][pt.y]!!.setImageResource(R.drawable.white)
                                boardView[pt.x][pt.y+1]!!.setImageResource(R.drawable.white)
                                boardView[pt.x][pt.y-1]!!.setImageResource(R.drawable.white)
                                boardView[pt.x-1][pt.y+1]!!.setImageResource(R.drawable.white)
                            }catch (e:ArrayIndexOutOfBoundsException ) {
                                //se a peça passou das bordas eu vou parar o jogo
                                running = true
                            }
                        }else if ( cont == 3){
                            try {
                                boardView[pt.x][pt.y]!!.setImageResource(R.drawable.white)
                                boardView[pt.x+1][pt.y]!!.setImageResource(R.drawable.white)
                                boardView[pt.x-1][pt.y]!!.setImageResource(R.drawable.white)
                                boardView[pt.x+1][pt.y+1]!!.setImageResource(R.drawable.white)
                            }catch (e:ArrayIndexOutOfBoundsException ) {
                                //se a peça passou das bordas eu vou parar o jogo
                                running = true
                            }
                        }else if(cont == 4){
                            cont = 0
                        }

                    }else if(peca == 6){
                        if(cont == 0){
                            try {
                                boardView[pt.x][pt.y]!!.setImageResource(R.drawable.white)
                                boardView[pt.x][pt.y+1]!!.setImageResource(R.drawable.white)
                                boardView[pt.x][pt.y-1]!!.setImageResource(R.drawable.white)
                                boardView[pt.x-1][pt.y-1]!!.setImageResource(R.drawable.white)
                            }catch (e:ArrayIndexOutOfBoundsException ) {
                                //se a peça passou das bordas eu vou parar o jogo
                                running = true
                            }
                        }else if(cont ==1){
                            try {
                                boardView[pt.x][pt.y]!!.setImageResource(R.drawable.white)
                                boardView[pt.x+1][pt.y]!!.setImageResource(R.drawable.white)
                                boardView[pt.x-1][pt.y]!!.setImageResource(R.drawable.white)
                                boardView[pt.x-1][pt.y+1]!!.setImageResource(R.drawable.white)
                            }catch (e:ArrayIndexOutOfBoundsException ) {
                                //se a peça passou das bordas eu vou parar o jogo
                                running = true
                            }
                        }else if(cont ==2){
                            try {
                                boardView[pt.x][pt.y]!!.setImageResource(R.drawable.white)
                                boardView[pt.x][pt.y-1]!!.setImageResource(R.drawable.white)
                                boardView[pt.x][pt.y+1]!!.setImageResource(R.drawable.white)
                                boardView[pt.x+1][pt.y+1]!!.setImageResource(R.drawable.white)
                            }catch (e:ArrayIndexOutOfBoundsException ) {
                                //se a peça passou das bordas eu vou parar o jogo
                                running = true
                            }
                        }else if(cont == 3){
                            try {
                                boardView[pt.x][pt.y]!!.setImageResource(R.drawable.white)
                                boardView[pt.x-1][pt.y]!!.setImageResource(R.drawable.white)
                                boardView[pt.x+1][pt.y]!!.setImageResource(R.drawable.white)
                                boardView[pt.x+1][pt.y-1]!!.setImageResource(R.drawable.white)
                            }catch (e:ArrayIndexOutOfBoundsException ) {
                                //se a peça passou das bordas eu vou parar o jogo
                                running = true
                            }
                        }else if(cont == 4){
                            cont = 0
                        }

                    }else if(peca == 7){
                        if(cont == 0){
                            try {
                                boardView[pt.x][pt.y]!!.setImageResource(R.drawable.white)
                                boardView[pt.x-1][pt.y]!!.setImageResource(R.drawable.white)
                                boardView[pt.x][pt.y+1]!!.setImageResource(R.drawable.white)
                                boardView[pt.x+1][pt.y+1]!!.setImageResource(R.drawable.white)
                            }catch (e:ArrayIndexOutOfBoundsException ) {
                                //se a peça passou das bordas eu vou parar o jogo
                                running = true
                            }
                        }else if(cont == 1){
                            try {
                                boardView[pt.x][pt.y]!!.setImageResource(R.drawable.white)
                                boardView[pt.x+1][pt.y]!!.setImageResource(R.drawable.white)
                                boardView[pt.x+1][pt.y-1]!!.setImageResource(R.drawable.white)
                                boardView[pt.x][pt.y+1]!!.setImageResource(R.drawable.white)
                            }catch (e:ArrayIndexOutOfBoundsException ) {
                                //se a peça passou das bordas eu vou parar o jogo
                                running = true
                            }
                        }else if(cont == 2){
                            cont = 0
                        }

                    }

                }
            }
        }.start()

        cont = 0
    }

    override fun onStop() {
        super.onStop()
        val setting = getSharedPreferences(PREFS,Context.MODE_PRIVATE)
        with((setting.edit())){
            putInt("Recorde", recorde)
            //putInt("pontos", null)
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //implementar
        //outState.putIntArray("Jogo", )
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        //implementar
    }
}
