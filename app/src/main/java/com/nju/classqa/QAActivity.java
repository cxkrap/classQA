package com.nju.classqa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nju.classqa.vo.Answer;

import java.util.ArrayList;
import java.util.List;

public class QAActivity extends Activity {
    private List<Answer> answerList=new ArrayList<>();
    private Button sendAnswer;
    private RecyclerView answerRecyclerView;
    private AnswerAdapter adapter;
    private int questionId;
    private EditText inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answerlist);
        Intent inintent=getIntent();
        questionId=inintent.getIntExtra("questionId",0);
        inputText=(EditText)findViewById(R.id.input_answer);
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
                //Todo
                String content=inputText.getText().toString();
                if(!content.equals("")){
                    Answer answer=new Answer(content,0);
                    answerList.add(0,answer);
                    adapter.notifyItemInserted(0);
                    answerRecyclerView.scrollToPosition(0);
                    inputText.setText("");
                }
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
