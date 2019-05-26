package com.nju.classqa;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class CourseQuesActivity extends Activity {
    private List<Question> questionList=new ArrayList<>();
    private Button send;
    private RecyclerView questionRecyclerView;
    private QuestionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionlist);
        initCourse();
        send=(Button)findViewById(R.id.send);
        questionRecyclerView=(RecyclerView)findViewById(R.id.question_recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        questionRecyclerView.setLayoutManager(layoutManager);
        adapter=new QuestionAdapter(questionList);
        questionRecyclerView.setAdapter(adapter);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private void initCourse(){
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
