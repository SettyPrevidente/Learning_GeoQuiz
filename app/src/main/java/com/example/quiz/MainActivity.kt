package com.example.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewDebug.IntToString
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import kotlin.math.abs

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var beforeButton: ImageButton
    private lateinit var questionTextView: TextView
    private lateinit var rightAnswersTextView: TextView

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )

    private var currentIndex = 0
    private var rightAnswers = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        setContentView(R.layout.activity_main)
        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)

        nextButton = findViewById(R.id.next_button)
        beforeButton = findViewById(R.id.bedore_button)
        beforeButton.setEnabled(false)

        questionTextView = findViewById(R.id.question_text_view)

        trueButton.setOnClickListener { view: View ->
            checkAnswer(true)
        }

        falseButton.setOnClickListener { view: View ->
            checkAnswer(false)
        }

        nextButton.setOnClickListener { view: View ->
            currentIndex = abs((currentIndex + 1) % questionBank.size)
            updateQuestion()
        }

        questionTextView.setOnClickListener { view: View ->
            currentIndex = abs((currentIndex + 1) % questionBank.size)
            updateQuestion()
        }

        beforeButton.setOnClickListener { view: View ->
            currentIndex = abs((currentIndex - 1) % questionBank.size)
            updateQuestion()
        }

        updateQuestion()

    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)
        trueButton.setEnabled(true)
        falseButton.setEnabled(true)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer
        val messageResId = if (userAnswer == correctAnswer) {R.string.correct_toast}
                            else {R.string.incorrect_toast}

        Toast.makeText(
            this,
            messageResId,
            Toast.LENGTH_SHORT)
            .apply { setGravity(Gravity.CENTER, 0, 0); show() }
        trueButton.setEnabled(false)
        falseButton.setEnabled(false)
    }

}