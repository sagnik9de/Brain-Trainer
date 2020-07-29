package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startbutton;//to hide the button
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button playAgainButton;
    ArrayList<Integer> answers=new ArrayList<Integer>();
    TextView answerTextView;
    TextView pointTextView;
    TextView sumTextView;
    TextView timer;
   ConstraintLayout gameLayout;

    int numberOfQuestions;
    int locationOfCorrectAnswer;  //this is will the location of the random number
    int score=0;

    public void playagain(View view){
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        button4.setEnabled(true);
        score=0;
        numberOfQuestions=0;
        timer.setText("30s");
        pointTextView.setText("0/0");
        answerTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        generateQuestions();

        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                timer.setText("0s");
                answerTextView.setText("Your score is " + Integer.toString(score) +"/" +Integer.toString(numberOfQuestions));
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
                button4.setEnabled(false);
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void generateQuestions(){
        Random rand=new Random();
        int a=rand.nextInt(25);  //to generate random numbers between 0 to 24
        int b=rand.nextInt(25);
        sumTextView.setText(Integer.toString(a)+ " + "+Integer.toString(b));
        locationOfCorrectAnswer=rand.nextInt(4); //the location will vary in between 0-3 since there are only 4 places
        answers.clear(); //this will clear the previous array list and a new array list will be created every time
        int incorrectAnswer;
        for(int i=0;i<4;i++){
            if(i==locationOfCorrectAnswer)
            {
                answers.add(a+b);  //adding the correct answer to the list
            }
            else
            {
                incorrectAnswer=rand.nextInt(51); //this will generate the random incorrect answer
                while(incorrectAnswer==a+b){   //while loop will keep on generating a new random incorrect answer if the correct ans is equal to incorrect ans
                    incorrectAnswer=rand.nextInt(51);
                }
                answers.add(incorrectAnswer);   //adding the incorrect answer to the list
            }
        }
        button1.setText(Integer.toString(answers.get(0)));
        button2.setText(Integer.toString(answers.get(1)));
        button3.setText(Integer.toString(answers.get(2)));
        button4.setText(Integer.toString(answers.get(3)));


    }
    public void chooseAnswer(View view){
        //Log.i("Tag",(String)view.getTag());
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            score++;
            answerTextView.setText("CORRECT!");

        }
        else{
            answerTextView.setText("WRONG!");
        }
        numberOfQuestions++;
        pointTextView.setText(Integer.toString(score) +"/" +Integer.toString(numberOfQuestions));
        generateQuestions(); //a new question will be generated as soon as the previous answer is generated.
    }
    public void start(View view){
        startbutton.setVisibility(View.INVISIBLE); //on tapping the button the button disappears
        gameLayout.setVisibility(ConstraintLayout.VISIBLE);
        playagain(findViewById(R.id.playAgainButton));


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startbutton=(Button)findViewById(R.id.startbutton);
        sumTextView=(TextView)findViewById(R.id.sumTextView);
         button1=(Button)findViewById(R.id.button1);
         button2=(Button)findViewById(R.id.button2);
         button3=(Button)findViewById(R.id.button3);
         button4=(Button)findViewById(R.id.button4);
         answerTextView=(TextView)findViewById(R.id.answerTextView);
         pointTextView=(TextView)findViewById(R.id.pointTextView);
         //generateQuestions(); //a new question will be generated at the start of the app
         playAgainButton=(Button)findViewById(R.id.playAgainButton);
        timer=(TextView)findViewById(R.id.timer);

         gameLayout=(ConstraintLayout) findViewById(R.id.gameLayout);




    }
}
