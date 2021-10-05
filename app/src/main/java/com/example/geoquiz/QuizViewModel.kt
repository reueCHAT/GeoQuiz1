package com.example.geoquiz
import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"
class QuizViewModel: ViewModel() {

    val pictureBank= mutableListOf<QuestionPicture>(
        QuestionPicture(R.drawable.australia, R.string.question_australia),
        QuestionPicture(R.drawable.ocean, R.string.question_ocean),
        QuestionPicture(R.drawable.mideast, R.string.question_mideast),
        QuestionPicture(R.drawable.africa, R.string.question_africa),
        QuestionPicture(R.drawable.america, R.string.question_americas),
        QuestionPicture(R.drawable.asia, R.string.question_asia)
    )

    val questionBank= mutableListOf<Question>(
        //Создает массив вопросов
        Question(R.string.question_australia, true,picture=pictureBank[0]),
        Question(R.string.question_ocean, true,picture = pictureBank[1]),
        Question(R.string.question_mideast, false,picture = pictureBank[2]),
        Question(R.string.question_africa,false,picture = pictureBank[3]),
        Question(R.string.question_asia, true,picture = pictureBank[4]),
    )

    var counter_of_answer: Int =0
    var counter_of_correct_answer: Int =0
    var currentIndex:Int =0

    val currenQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer
    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId
    val currentQuestionPicture: Int
        get() = questionBank[currentIndex].picture.image

    fun moveToNext(){
        currentIndex=(currentIndex +1) % questionBank.size
    }
}