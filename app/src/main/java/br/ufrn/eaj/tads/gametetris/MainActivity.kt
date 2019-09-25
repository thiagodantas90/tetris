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
import android.widget.Toast
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    var PREFS = toString()
    var recorde:Long=0
    var pontos:Long = 200

    val LINHA = 36
    val COLUNA = 26
    var running = true
    var speed:Long = 300

    var peca = 7
    var cont = 0
    var contL = 0
    var contJ= 0

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

        //recuperando o recorde
        val settings = getSharedPreferences(PREFS, Context.MODE_PRIVATE)

        recorde = settings.getLong("recorde", 0)
        recordeId.text = recorde.toString()

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
        if(pt.y<23){
            pt.moveRight()
        }
    }
    fun esquerda(v:View){
        if(pt.y>1){
            pt.moveLeft()
        }
    }
    //gira a peça
    fun girar(v:View){
       cont++
    }
    fun cair(v:View){
        speed = 50
    }
    fun pausa(v:View){
        finish()
    }
    fun gerarPeca(){
        peca = Random.nextInt(1, 7)
        pt = Ponto(0,15)
        speed = 300
    }

    fun gameRun(){
        for(i in 0 until COLUNA){
            board[35][i] = 1
            boardView[35][i]!!.setImageResource(R.drawable.gray)
        }
        for(i in 0 until LINHA-1){
            board[i][0] = 1
            board[i][25] = 1
            boardView[i][0]!!.setImageResource(R.drawable.gray)
            boardView[i][25]!!.setImageResource(R.drawable.gray)
        }
        Thread{
            //speed  = 300
            while(running){


                Thread.sleep(speed)

                runOnUiThread{
                    //limpa tela
                    for (i in 0 until LINHA-1) {
                        for (j in 1 until COLUNA-1) {
                            boardView[i][j]!!.setImageResource(R.drawable.black)
                        }
                    }
                    //Colocar peças
                    for (i in 0 until LINHA-1) {
                        for (j in 1 until COLUNA-1) {
                            if(board[i][j] == 1){
                                boardView[i][j]!!.setImageResource(R.drawable.white)
                            }
                        }
                    }

                    //teste de linha

                    for (i in 0 until LINHA-1) {
                        for (j in 0 until COLUNA) {
                            if(board[i][j] == 1){
                                contL++
                            }
                            contJ = j
                        }
                        if(contL == 19){
                            pontos++
                            for(i in 0 until COLUNA){
                                board[contJ][i] = 0
                            }
                            //fazer teste pra descer as peças
                        }
                    }

                    //move peça atual

                    pt.moveDown()

                    //print peça
                    if(peca == 1){
                        if(cont == 0){
                            boardView[pt.x][pt.y]!!.setImageResource(R.drawable.white)
                            boardView[pt.x-1][pt.y]!!.setImageResource(R.drawable.white)
                            boardView[pt.x][pt.y+1]!!.setImageResource(R.drawable.white)
                            boardView[pt.x+1][pt.y]!!.setImageResource(R.drawable.white)

                            if(board[pt.x+2][pt.y]==1 || board[pt.x+1][pt.y+1]==1) {
                                board[pt.x][pt.y] = 1
                                board[pt.x - 1][pt.y] = 1
                                board[pt.x][pt.y + 1] = 1
                                board[pt.x + 1][pt.y] = 1
                                gerarPeca()
                            }
                        }else if (cont == 1){
                            boardView[pt.x][pt.y]!!.setImageResource(R.drawable.white)
                            boardView[pt.x][pt.y-1]!!.setImageResource(R.drawable.white)
                            boardView[pt.x][pt.y+1]!!.setImageResource(R.drawable.white)
                            boardView[pt.x+1][pt.y]!!.setImageResource(R.drawable.white)

                            if(board[pt.x+2][pt.y]==1 || board[pt.x+1][pt.y+1]==1 || board[pt.x+1][pt.y-1]==1) {
                                board[pt.x][pt.y] = 1
                                board[pt.x][pt.y - 1] = 1
                                board[pt.x][pt.y + 1] = 1
                                board[pt.x + 1][pt.y] = 1
                                gerarPeca()
                            }
                        }else if(cont == 2){
                            cont = 0
                        }
                    }else if(peca == 2){
                        boardView[pt.x][pt.y]!!.setImageResource(R.drawable.white)
                        boardView[pt.x+1][pt.y]!!.setImageResource(R.drawable.white)
                        boardView[pt.x][pt.y-1]!!.setImageResource(R.drawable.white)
                        boardView[pt.x+1][pt.y-1]!!.setImageResource(R.drawable.white)

                        if(board[pt.x+2][pt.y]==1 || board[pt.x+2][pt.y+1]==1 ) {
                            board[pt.x][pt.y] = 1
                            board[pt.x+1][pt.y] =1
                            board[pt.x][pt.y-1]=1
                            board[pt.x+1][pt.y-1]=1
                            gerarPeca()
                        }
                    }else if(peca == 3){
                        if(cont == 0){
                            boardView[pt.x][pt.y]!!.setImageResource(R.drawable.white)
                            boardView[pt.x+1][pt.y]!!.setImageResource(R.drawable.white)
                            boardView[pt.x][pt.y-1]!!.setImageResource(R.drawable.white)
                            boardView[pt.x-1][pt.y-1]!!.setImageResource(R.drawable.white)

                            if(board[pt.x+2][pt.y]==1 || board[pt.x+1][pt.y-1]==1) {
                                board[pt.x][pt.y] = 1
                                board[pt.x+1][pt.y] =1
                                board[pt.x][pt.y-1] =1
                                board[pt.x-1][pt.y-1] =1
                                gerarPeca()
                            }
                        }else if(cont == 1){
                            boardView[pt.x][pt.y]!!.setImageResource(R.drawable.white)
                            boardView[pt.x+1][pt.y]!!.setImageResource(R.drawable.white)
                            boardView[pt.x][pt.y+1]!!.setImageResource(R.drawable.white)
                            boardView[pt.x+1][pt.y-1]!!.setImageResource(R.drawable.white)

                            if(board[pt.x+2][pt.y]==1 || board[pt.x+1][pt.y+1]==1 || board[pt.x+1][pt.y-1]==1) {
                                board[pt.x][pt.y] = 1
                                board[pt.x+1][pt.y] = 1
                                board[pt.x][pt.y+1] = 1
                                board[pt.x+1][pt.y-1] = 1
                                gerarPeca()
                            }
                        }
                        else if(cont == 2){
                            cont = 0
                        }

                    }else if(peca == 4){
                        if(cont == 0){

                            boardView[pt.x][pt.y+1]!!.setImageResource(R.drawable.white)
                            boardView[pt.x][pt.y-1]!!.setImageResource(R.drawable.white)
                            boardView[pt.x][pt.y-2]!!.setImageResource(R.drawable.white)
                            boardView[pt.x][pt.y]!!.setImageResource(R.drawable.white)

                            if(board[pt.x+1][pt.y]==1 || board[pt.x+1][pt.y-1]==1 || board[pt.x+1][pt.y+1]==1 || board[pt.x+1][pt.y-2]==1 ) {
                                board[pt.x][pt.y+1] = 1
                                board[pt.x][pt.y-1] = 1
                                board[pt.x][pt.y-2] = 1
                                board[pt.x][pt.y] = 1
                                gerarPeca()
                            }

                        }else if(cont == 1){

                            boardView[pt.x+1][pt.y]!!.setImageResource(R.drawable.white)
                            boardView[pt.x-1][pt.y]!!.setImageResource(R.drawable.white)
                            boardView[pt.x+2][pt.y]!!.setImageResource(R.drawable.white)
                            boardView[pt.x][pt.y]!!.setImageResource(R.drawable.white)
                            if(board[pt.x+2][pt.y]==1) {
                                board[pt.x+1][pt.y] = 1
                                board[pt.x-2][pt.y] = 1
                                board[pt.x-1][pt.y] = 1
                                board[pt.x][pt.y] = 1
                                gerarPeca()
                            }
                        }else if(cont == 2){
                            cont = 0
                        }

                    }else if(peca == 5){
                        if(cont == 0){

                            boardView[pt.x][pt.y]!!.setImageResource(R.drawable.white)
                            boardView[pt.x][pt.y+1]!!.setImageResource(R.drawable.white)
                            boardView[pt.x][pt.y-1]!!.setImageResource(R.drawable.white)
                            boardView[pt.x+1][pt.y-1]!!.setImageResource(R.drawable.white)

                            if(board[pt.x+1][pt.y]==1 || board[pt.x+1][pt.y+1]==1 || board[pt.x+2][pt.y-1]==1 ) {
                                board[pt.x][pt.y] = 1
                                board[pt.x][pt.y+1] = 1
                                board[pt.x][pt.y-1] = 1
                                board[pt.x+1][pt.y-1] = 1
                                gerarPeca()
                            }

                        }else if(cont == 1){

                            boardView[pt.x][pt.y]!!.setImageResource(R.drawable.white)
                            boardView[pt.x+1][pt.y]!!.setImageResource(R.drawable.white)
                            boardView[pt.x-1][pt.y]!!.setImageResource(R.drawable.white)
                            boardView[pt.x-1][pt.y-1]!!.setImageResource(R.drawable.white)

                            if(board[pt.x+2][pt.y]==1 || board[pt.x][pt.y-1]==1) {
                                board[pt.x][pt.y] = 1
                                board[pt.x+1][pt.y] =1
                                board[pt.x-1][pt.y] =1
                                board[pt.x-1][pt.y-1] =1
                                gerarPeca()
                            }
                        }else if(cont == 2){
                            cont = 0
                        }

                    }else if(peca == 6){
                        if(cont == 0){
                                boardView[pt.x][pt.y]!!.setImageResource(R.drawable.white)
                                boardView[pt.x][pt.y+1]!!.setImageResource(R.drawable.white)
                                boardView[pt.x][pt.y-1]!!.setImageResource(R.drawable.white)
                                boardView[pt.x-1][pt.y-1]!!.setImageResource(R.drawable.white)

                            if(board[pt.x+1][pt.y]==1 || board[pt.x+1][pt.y-1]==1 ||board[pt.x+1][pt.y+1]==1) {
                                board[pt.x][pt.y] = 1
                                board[pt.x][pt.y+1] =1
                                board[pt.x][pt.y-1] =1
                                board[pt.x-1][pt.y-1] =1
                                gerarPeca()
                            }
                        }else if(cont ==1){

                                boardView[pt.x][pt.y]!!.setImageResource(R.drawable.white)
                                boardView[pt.x+1][pt.y]!!.setImageResource(R.drawable.white)
                                boardView[pt.x-1][pt.y]!!.setImageResource(R.drawable.white)
                                boardView[pt.x-1][pt.y+1]!!.setImageResource(R.drawable.white)

                            if(board[pt.x+2][pt.y]==1 || board[pt.x][pt.y+1]==1 ) {
                                board[pt.x][pt.y] = 1
                                board[pt.x+1][pt.y] = 1
                                board[pt.x-1][pt.y] = 1
                                board[pt.x-1][pt.y+1] = 1
                                gerarPeca()
                            }
                        }else if(cont == 2){
                            cont = 0
                        }

                    }else if(peca == 7){
                        if(cont == 0){

                                boardView[pt.x][pt.y]!!.setImageResource(R.drawable.white)
                                boardView[pt.x-1][pt.y]!!.setImageResource(R.drawable.white)
                                boardView[pt.x][pt.y+1]!!.setImageResource(R.drawable.white)
                                boardView[pt.x+1][pt.y+1]!!.setImageResource(R.drawable.white)

                            if(board[pt.x+1][pt.y]==1 || board[pt.x+2][pt.y+1]==1 ) {
                                board[pt.x][pt.y] = 1
                                board[pt.x-1][pt.y] = 1
                                board[pt.x][pt.y+1] = 1
                                board[pt.x+1][pt.y+1] = 1
                                gerarPeca()
                            }
                        }else if(cont == 1){

                                boardView[pt.x][pt.y]!!.setImageResource(R.drawable.white)
                                boardView[pt.x+1][pt.y]!!.setImageResource(R.drawable.white)
                                boardView[pt.x+1][pt.y-1]!!.setImageResource(R.drawable.white)
                                boardView[pt.x][pt.y+1]!!.setImageResource(R.drawable.white)

                            if(board[pt.x+2][pt.y]==1 || board[pt.x+2][pt.y-1]==1 || board[pt.x][pt.y+1]==1 ) {
                                board[pt.x][pt.y] = 1
                                board[pt.x+1][pt.y] =1
                                board[pt.x+1][pt.y-1] =1
                                board[pt.x][pt.y+1] =1
                                gerarPeca()
                            }
                        }else if(cont == 2){
                            cont = 0
                        }

                    }
                    //

                }
            }
        }.start()

        cont = 0
    }
    override fun onStop() {
        super.onStop()
        val setting = getSharedPreferences(PREFS,Context.MODE_PRIVATE)
        var editor = setting.edit()
//        editor.putLong("recorde", 125)
//        editor.commit()
        if(pontos>recorde){
            editor.putLong("recorde", pontos)
            editor.commit()
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
