package com.nju.classqa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class CourseQuesActivity extends Activity {
    private List<Question> questionList=new ArrayList<>();
    private Button sendQuestion;
    private RecyclerView questionRecyclerView;
    private QuestionAdapter adapter;
    private EditText inputText;
    private int courseId;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionlist);
        Intent inintent=getIntent();
        courseId=inintent.getIntExtra("courseId",0);
        initQuestion();
        sendQuestion=(Button)findViewById(R.id.send_question);
        inputText=(EditText)findViewById(R.id.input_question);
        questionRecyclerView=(RecyclerView)findViewById(R.id.question_recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        questionRecyclerView.setLayoutManager(layoutManager);
        adapter=new QuestionAdapter(questionList);
        questionRecyclerView.setAdapter(adapter);
        sendQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Todo
                String content=inputText.getText().toString();
                if(!content.equals("")){
                    Question question=new Question(content,0);
                    questionList.add(0,question);
                    adapter.notifyItemInserted(0);
                    questionRecyclerView.scrollToPosition(0);
                    inputText.setText("");
                }
            }
        });

    }

    private void initQuestion(){
        questionList.add(new Question("沙雕"));
        questionList.add(new Question("沙雕"));
        questionList.add(new Question("沙雕"));
        questionList.add(new Question("沙雕"));
        questionList.add(new Question("沙雕"));
        questionList.add(new Question("沙雕"));
        questionList.add(new Question("沙雕"));
        questionList.add(new Question("沙雕"));
        questionList.add(new Question("沙雕"));
        questionList.add(new Question("沙雕"));
        questionList.add(new Question("沙雕"));
        questionList.add(new Question("沙雕"));
    }
}
