package com.nju.classqa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class CourseListActivity extends Activity  {
    private List<Course>courseList=new ArrayList<>();
    private FloatingActionButton addCourse;
    private RecyclerView courseRecyclerView;
    private CourseAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.courselist);
        initCourse();
        addCourse=(FloatingActionButton)findViewById(R.id.addCourse);
        courseRecyclerView=(RecyclerView)findViewById(R.id.course_recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        courseRecyclerView.setLayoutManager(layoutManager);
        adapter=new CourseAdapter(courseList);
        courseRecyclerView.setAdapter(adapter);
        addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private void initCourse(){
        courseList.add(new Course("计算系统基础","王浩然"));
        courseList.add(new Course("离散数学","马晓星"));
        courseList.add(new Course("软件工程与计算I","刘钦"));
        courseList.add(new Course("数据结构与计算","伏晓"));
        courseList.add(new Course("计算机组织与结构","任桐伟"));
        courseList.add(new Course("数据科学基础","陈振宇"));
        courseList.add(new Course("软件工程与计算II","刘钦"));
        courseList.add(new Course("互联网计算","刘峰"));
    }
}
