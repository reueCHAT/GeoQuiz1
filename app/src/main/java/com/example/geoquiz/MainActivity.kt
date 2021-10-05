package com.example.geoquiz
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.lifecycle.ViewModelProviders

private const val TAG = "MainActivity.kt"

class MainActivity : AppCompatActivity() {
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var previousButton: ImageButton
    private lateinit var questionTextView: TextView
    private lateinit var questionImageView: ImageView
    private lateinit var winTextView: TextView
    private val quizViewModel:QuizViewModel by lazy {
        ViewModelProviders.of(this).get(QuizViewModel::class.java)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"onCreate(Bundle?) called")
        setContentView(R.layout.activity_main)

        trueButton=findViewById(R.id.true_button)
        falseButton=findViewById(R.id.false_button)
        nextButton=findViewById(R.id.next_button)
        previousButton=findViewById(R.id.previous_button)
        questionTextView=findViewById(R.id.question_text_view)
        winTextView=findViewById(R.id.textViewWin)
        questionImageView=findViewById(R.id.imageView_pic)
        val controller=ViewController(quizViewModel,trueButton,falseButton,nextButton,previousButton,questionTextView,questionImageView,winTextView)


        trueButton.setOnClickListener {
            controller.checkAnswer(true)
            Toast.makeText(this, controller.messageResId, Toast.LENGTH_SHORT).show()
        }

        falseButton.setOnClickListener {
            controller.checkAnswer(false)
            Toast.makeText(this, controller.messageResId, Toast.LENGTH_SHORT).show()
        }
        nextButton.setOnClickListener(){
            quizViewModel.moveToNext()
            controller.updateQuestion()
        }
        previousButton.setOnClickListener(){
            if (quizViewModel.currentIndex==0)
                quizViewModel.currentIndex= quizViewModel.questionBank.size-1
            else
                quizViewModel.currentIndex= (quizViewModel.currentIndex-1) % quizViewModel.questionBank.size
            controller.updateQuestion()
        }
        questionTextView.setOnClickListener(){
            quizViewModel.moveToNext()
            controller.updateQuestion()
        }
        controller.updateQuestion()
    }

    override fun onStart(){
        super.onStart()
        Log.d(TAG,"onStart() called")
    }
    override fun onResume(){
        super.onResume()
        Log.d(TAG,"onResume() called")
    }
    override fun onPause(){
        super.onPause()
        Log.d(TAG,"onPause() called")
    }
    override fun onStop(){
        super.onStop()
        Log.d(TAG,"onStop() called")
    }
    override fun onDestroy(){
        super.onDestroy()
        Log.d(TAG,"onDestroy() called")
    }
}