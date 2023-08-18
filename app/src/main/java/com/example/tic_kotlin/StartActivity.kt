package com.example.tic_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

//import kotlinx.android.synthetic.main.activity_start.btn1


class StartActivity : AppCompatActivity() {


    private val buttons = ArrayList<Button>()
    private var currentPlayer = "X"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)



            buttons.apply {
                add(findViewById(R.id.btn1))
                add(findViewById(R.id.btn2))
                add(findViewById(R.id.btn3))
                add(findViewById(R.id.btn4))
                add(findViewById(R.id.btn5))
                add(findViewById(R.id.btn6))
                add(findViewById(R.id.btn7))
                add(findViewById(R.id.btn8))
                add(findViewById(R.id.btn9))
            }

            buttons.forEach { button ->
                button.setOnClickListener {
                    onButtonClick(button)
                }
            }
        }

//    private fun onButtonClick(button: Button) {
//        if (button.text.isEmpty()) {
//            button.text = currentPlayer
//            totalMoves++
//            if (checkWinner()) {
//                showWinner(currentPlayer)
//            } else if (totalMoves == buttons.size) {
//                showDraw()
//            } else {
//                button.setBackgroundResource(
//                    if (currentPlayer == "X") R.drawable.green_button_background
//                    else R.drawable.yellow_button_background
//                )
//                currentPlayer = if (currentPlayer == "X") "O" else "X"
//            }
//        }
//    }

    private fun onButtonClick(button: Button) {
        if (button.text.isEmpty()) {
            button.text = currentPlayer
            totalMoves++
            if (checkWinner()) {
                setWinningButtonsColor()
                showWinner(currentPlayer)
            } else if (totalMoves == buttons.size) {
                showDraw()
            } else {
                button.setBackgroundResource(
                    if (currentPlayer == "X") R.drawable.red_backgroud_color
                    else R.drawable.blue_button_background
                )
                currentPlayer = if (currentPlayer == "X") "O" else "X"
            }
        }
    }

    private fun setWinningButtonsColor() {
        val winningCombination = findWinningCombination()
        if (winningCombination != null) {
            val winningPlayer = buttons[winningCombination[0]].text.toString()
            val colorResource = if (winningPlayer == "X") R.drawable.green_button_background else R.drawable.yellow_background
            winningCombination.forEach { index ->
                buttons[index].setBackgroundResource(colorResource)
            }
        }
    }


    private fun findWinningCombination(): IntArray? {
        val winningCombinations = arrayOf(
            intArrayOf(0, 1, 2), intArrayOf(3, 4, 5), intArrayOf(6, 7, 8),
            intArrayOf(0, 3, 6), intArrayOf(1, 4, 7), intArrayOf(2, 5, 8),
            intArrayOf(0, 4, 8), intArrayOf(2, 4, 6)
        )

        for (combo in winningCombinations) {
            val (a, b, c) = combo
            if (buttons[a].text == buttons[b].text && buttons[b].text == buttons[c].text && buttons[a].text.isNotEmpty()) {
                return combo
            }
        }

        return null
    }



    private fun checkWinner(): Boolean {
            val winningCombinations = arrayOf(
                intArrayOf(0, 1, 2), intArrayOf(3, 4, 5), intArrayOf(6, 7, 8),
                intArrayOf(0, 3, 6), intArrayOf(1, 4, 7), intArrayOf(2, 5, 8),
                intArrayOf(0, 4, 8), intArrayOf(2, 4, 6)
            )

            for (combo in winningCombinations) {
                val (a, b, c) = combo
                if (buttons[a].text == buttons[b].text && buttons[b].text == buttons[c].text && buttons[a].text.isNotEmpty()) {
                    return true
                }
            }

            return false
        }

        private fun showWinner(player: String) {
            val winnerMessage = "Player $player wins!"
            Toast.makeText(this, winnerMessage, Toast.LENGTH_SHORT).show()
            disableButtons()
        }

    private fun showDraw() {
        val drawMessage = "It's a draw!"
        Toast.makeText(this, drawMessage, Toast.LENGTH_SHORT).show()
        disableButtons()
    }


    private fun disableButtons() {
            buttons.forEach { button ->
                button.isEnabled = false
            }


            val restartButton = findViewById<Button>(R.id.restartButton)
            val homeButton = findViewById<Button>(R.id.homeButton)

            restartButton.setOnClickListener {
                restartGame()
            }

            homeButton.setOnClickListener {
                navigateToHome()
            }
        }

    // ... your existing code ...
//
//    private fun restartGame() {
//        buttons.forEach { button ->
//            button.text = ""
//            button.setBackgroundColor(R.drawable.white_button_color)
//            button.isEnabled = true
//        }
//        currentPlayer = "X"
//        totalMoves = 0
//    }
    private fun restartGame() {
        buttons.forEach { button ->
            button.text = ""
            button.setBackgroundResource(R.drawable.white_button_color)
            button.isEnabled = true
        }

        val restartButton = findViewById<Button>(R.id.restartButton)
        restartButton.setBackgroundResource(R.drawable.restart_button_color) // Set background to white

        currentPlayer = "X"
        totalMoves = 0
    }


    private fun navigateToHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onResume() {
        super.onResume()

        val restartButton = findViewById<Button>(R.id.restartButton)
        val homeButton = findViewById<Button>(R.id.homeButton)

        restartButton.setOnClickListener {
            restartGame()
        }

        homeButton.setOnClickListener {
            navigateToHome()
        }
    }

    private var totalMoves = 0

}



