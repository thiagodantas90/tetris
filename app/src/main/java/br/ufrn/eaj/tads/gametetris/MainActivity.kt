package br.ufrn.eaj.tads.gametetris

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
import android.view.View
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    val LINHA = 36
    val COLUNA = 26
    var running = true
    var speed:Long = 300
    var peca = 3
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
        fun gira(){

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
    fun girar(v:View){
       cont++
    }
    fun gameRun(){
        //peca = Random.nextInt(1, 7)
        Thread{
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
//                                if(LINHA>36){
//                                    board[pt.x][pt.y] = 1
//                                    board[pt.x-1][pt.y] =  1
//                                    board[pt.x][pt.y+1] =  1
//                                    board[pt.x+1][pt.y] =  1
//                                }
                            }
                        }else if (cont == 1){
                            try {
                                boardView[pt.x][pt.y]!!.setImageResource(R.drawable.white)
                                boardView[pt.x][pt.y-1]!!.setImageResource(R.drawable.white)
                                boardView[pt.x][pt.y+1]!!.setImageResource(R.drawable.white)
                                boardView[pt.x+1][pt.y]!!.setImageResource(R.drawable.white)
                            }catch (e:ArrayIndexOutOfBoundsException ) {
                                //se a peça passou das bordas eu vou parar o jogo
                                running = false
                            }
                        }else if(cont == 2){
                            try {
                                boardView[pt.x][pt.y]!!.setImageResource(R.drawable.white)
                                boardView[pt.x][pt.y-1]!!.setImageResource(R.drawable.white)
                                boardView[pt.x-1][pt.y]!!.setImageResource(R.drawable.white)
                                boardView[pt.x+1][pt.y]!!.setImageResource(R.drawable.white)
                            }catch (e:ArrayIndexOutOfBoundsException ) {
                                //se a peça passou das bordas eu vou parar o jogo
                                running = false
                            }
                        }else if(cont == 3){
                            try {
                                boardView[pt.x][pt.y]!!.setImageResource(R.drawable.white)
                                boardView[pt.x][pt.y-1]!!.setImageResource(R.drawable.white)
                                boardView[pt.x-1][pt.y]!!.setImageResource(R.drawable.white)
                                boardView[pt.x][pt.y+1]!!.setImageResource(R.drawable.white)
                            }catch (e:ArrayIndexOutOfBoundsException ) {
                                //se a peça passou das bordas eu vou parar o jogo
                                running = false
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
                            running = false
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
                                running = false
                            }
                        }else if(cont == 1){
                            try {
                                boardView[pt.x][pt.y]!!.setImageResource(R.drawable.white)
                                boardView[pt.x+1][pt.y]!!.setImageResource(R.drawable.white)
                                boardView[pt.x][pt.y+1]!!.setImageResource(R.drawable.white)
                                boardView[pt.x+1][pt.y-1]!!.setImageResource(R.drawable.white)
                            }catch (e:ArrayIndexOutOfBoundsException ) {
                                //se a peça passou das bordas eu vou parar o jogo
                                running = false
                            }
                        }
                        else if(cont == 2){
                            cont = 0
                        }

                    }else if(peca == 4){
                        try {
                            boardView[pt.x][pt.y+1]!!.setImageResource(R.drawable.white)
                            boardView[pt.x][pt.y-1]!!.setImageResource(R.drawable.white)
                            boardView[pt.x][pt.y-2]!!.setImageResource(R.drawable.white)
                            boardView[pt.x][pt.y]!!.setImageResource(R.drawable.white)
                        }catch (e:ArrayIndexOutOfBoundsException ) {
                            //se a peça passou das bordas eu vou parar o jogo
                            running = false
                        }
                    }else if(peca == 5){
                        try {
                            boardView[pt.x][pt.y]!!.setImageResource(R.drawable.white)
                            boardView[pt.x][pt.y+1]!!.setImageResource(R.drawable.white)
                            boardView[pt.x][pt.y-1]!!.setImageResource(R.drawable.white)
                            boardView[pt.x+1][pt.y-1]!!.setImageResource(R.drawable.white)
                        }catch (e:ArrayIndexOutOfBoundsException ) {
                            //se a peça passou das bordas eu vou parar o jogo
                            running = false
                        }
                    }else if(peca == 6){
                        try {
                            boardView[pt.x][pt.y]!!.setImageResource(R.drawable.white)
                            boardView[pt.x][pt.y+1]!!.setImageResource(R.drawable.white)
                            boardView[pt.x][pt.y-1]!!.setImageResource(R.drawable.white)
                            boardView[pt.x-1][pt.y-1]!!.setImageResource(R.drawable.white)
                        }catch (e:ArrayIndexOutOfBoundsException ) {
                            //se a peça passou das bordas eu vou parar o jogo
                            running = false
                        }
                    }else if(peca == 7){
                        try {
                            boardView[pt.x][pt.y]!!.setImageResource(R.drawable.white)
                            boardView[pt.x-1][pt.y]!!.setImageResource(R.drawable.white)
                            boardView[pt.x][pt.y+1]!!.setImageResource(R.drawable.white)
                            boardView[pt.x+1][pt.y+1]!!.setImageResource(R.drawable.white)
                        }catch (e:ArrayIndexOutOfBoundsException ) {
                            //se a peça passou das bordas eu vou parar o jogo
                            running = false
                        }
                    }

                }
            }
        }.start()
    }
}
