package com.example.geoquiz

import android.view.View
import android.widget.*

class ViewController(val quizViewModel: QuizViewModel,
                     val trueButton: Button,
                     val falseButton: Button,
                     val nextButton: ImageButton,
                     val previousButton: ImageButton,
                     val questionTextView: TextView,
                     val questionImageView: ImageView,
                     val winTextView: TextView){

    var correctAnswer = quizViewModel.currenQuestionAnswer
    var correctness_of_answer: Int = 0
    var messageResId =0
    var questionTextResId =quizViewModel.currentQuestionText
    var questionPictureId =quizViewModel.currentQuestionPicture

    fun offClick(v: View) {
        v.isClickable = false
    }
    fun onClick(v: View){
        v.isClickable = true
    }

    fun updateQuestion(){ //Переводит счетчик вопроса на следующее значение не превышающее кол-во вопросов
        questionTextResId =quizViewModel.currentQuestionText
        questionPictureId =quizViewModel.currentQuestionPicture
        questionTextView.setText(questionTextResId)
        questionImageView.setImageResource(questionPictureId)
        if (quizViewModel.questionBank[quizViewModel.currentIndex].count_of_answer==0){
            onClick(trueButton)
            onClick(falseButton)
        } else {
            offClick(trueButton)
            offClick(falseButton)
        }
    }

    fun endGameCheck(userAnswerCost: Int){
        if(quizViewModel.counter_of_answer!= quizViewModel.questionBank.size-1){
            quizViewModel.counter_of_correct_answer+=userAnswerCost
            quizViewModel.counter_of_answer++
        } else {
            offClick(nextButton)
            offClick(previousButton)
            winTextView.setText("The test is over, number of correct answers = ${quizViewModel.counter_of_correct_answer}")
        }
    }

    fun checkAnswer(userAnswer: Boolean){
        correctAnswer = quizViewModel.currenQuestionAnswer
        correctness_of_answer= 0
        messageResId =0
        if (userAnswer == correctAnswer) {
            messageResId=R.string.correct_toast
            correctness_of_answer= 1
        } else {
            messageResId=R.string.incorrect_toast
            correctness_of_answer= 0
        }
        quizViewModel.questionBank[quizViewModel.currentIndex].count_of_answer = 1
        endGameCheck(correctness_of_answer)
        offClick(trueButton)
        offClick(falseButton)
        offClick(questionTextView)
    }

}