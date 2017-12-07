package ru.littlebrains.yesorno;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.javatechig.widgetdemo.R;

import java.util.Date;
import java.util.Random;

import trikita.log.Log;

public class MainActivity extends AppCompatActivity {

    private ImageButton send;
    private RecyclerView mRecyclerView;
    private QuestionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sendBroadcast(new Intent(Intent.ACTION_MAIN)
                .addCategory(Intent.CATEGORY_HOME));
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        send = (ImageButton) findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuestionModel questionModel = new QuestionModel();
                questionModel.question = ((EditText) findViewById(R.id.question_e)).getText().toString();
                Random rand = new Random();
                int a = rand.nextInt(2);
                questionModel.answer = a;
                Log.d(new Date().getTime()/1000, (int)(new Date().getTime()/1000));
                questionModel.timestamp = (int)(new Date().getTime()/1000);
                ((EditText) findViewById(R.id.question_e)).setText("");

                if(questionModel.question.length() > 0 && !questionModel.question.substring(questionModel.question.length()-1, questionModel.question.length()).equals("?")){
                    questionModel.question += "?";
                }
                new QuestionData(MainActivity.this).save(questionModel);
                viewQuestionList();
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        viewQuestionList();
        //new QuestionData(this).clearBD();
    }

    private void viewQuestionList(){
        adapter = new QuestionAdapter(this, new QuestionData(this).get());
        mRecyclerView.setAdapter(adapter);
    }
}
