package com.example.driodquest_lab_3

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.driodquest_lab_3.Question

class MainActivity : AppCompatActivity() {

    private lateinit var mTrueButton: Button
    private lateinit var mFalseButton: Button
    private lateinit var mNextButton: ImageButton
    private lateinit var mQuestionTextView: TextView
    private var mCorrectAnswers = 0
    private var mIsQuizFinished = false

    private val mQuestionBank = arrayOf(
        Question(R.string.question_text, true),
        Question(R.string.question_linear, true),
        Question(R.string.question_service, false),
        Question(R.string.question_res, true),
        Question(R.string.question_manifest, true)
    )

    private var mCurrentIndex = 0

    companion object {
        private const val KEY_INDEX = "index"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mTrueButton = findViewById(R.id.button_true)
        mFalseButton = findViewById(R.id.button_false)
        mNextButton = findViewById(R.id.next_button)
        mQuestionTextView = findViewById(R.id.question_text_view)

        savedInstanceState?.let {
            mCurrentIndex = it.getInt(KEY_INDEX, 0)
        }

        updateQuestion()

        mTrueButton.setOnClickListener {
            checkAnswer(true)
        }

        mFalseButton.setOnClickListener {
            checkAnswer(false)
        }

        mNextButton.setOnClickListener {
            mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.size
            if (mCurrentIndex == 0) {
                mIsQuizFinished = true // Устанавливаем флаг, что викторина завершена
            }
            updateQuestion()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_INDEX, mCurrentIndex)
    }

    private fun updateQuestion() {
        val question = mQuestionBank[mCurrentIndex].textResId
        mQuestionTextView.setText(question)
        if (mCurrentIndex == 0 && mIsQuizFinished) {
            // Выводим уведомление с количеством правильных ответов
            val message = "Вы ответили правильно на $mCorrectAnswers из ${mQuestionBank.size} вопросов."
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
            // Или используйте AlertDialog для более настраиваемого уведомления
            mIsQuizFinished = false // Сбрасываем флаг
            mCorrectAnswers = 0 // Сбрасываем счетчик правильных ответов
        }
    }

    private fun checkAnswer(userPressedTrue: Boolean) {
        val answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue()
        if (userPressedTrue == answerIsTrue) {
            mCorrectAnswers++
        }
        val messageResId = if (userPressedTrue == answerIsTrue) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }
}