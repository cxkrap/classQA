package com.nju.classqa;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class QAActivity extends Activity {
    private List<Answer> answerList=new ArrayList<>();
    private Button sendAnswer;
    private RecyclerView answerRecyclerView;
    private AnswerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answerlist);
        initAnswer();
        sendAnswer=(Button)findViewById(R.id.send_answer);
        answerRecyclerView=(RecyclerView)findViewById(R.id.answer_recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        answerRecyclerView.setLayoutManager(layoutManager);
        adapter=new AnswerAdapter(answerList);
        answerRecyclerView.setAdapter(adapter);
        sendAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private void initAnswer(){
        answerList.add(new Answer("够沙雕，做网友"));
        answerList.add(new Answer("够沙雕，做网友"));
        answerList.add(new Answer("够沙雕，做网友"));
        answerList.add(new Answer("够沙雕，做网友"));
        answerList.add(new Answer("够沙雕，做网友"));
        answerList.add(new Answer("够沙雕，做网友"));
        answerList.add(new Answer("够沙雕，做网友"));
        answerList.add(new Answer("够沙雕，做网友"));
        answerList.add(new Answer("够沙雕，做网友"));
        answerList.add(new Answer("够沙雕，做网友"));
    }

}
