package com.nju.classqa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.nju.classqa.util.HttpUtil;
import com.nju.classqa.vo.Answer;
import com.nju.classqa.vo.Course;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CourseListActivity extends Activity  {
    private List<Course>courseList=new ArrayList<>();
    private FloatingActionButton addCourse;
    private RecyclerView courseRecyclerView;
    private CourseAdapter adapter;
    private int identity;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.courselist);
        Intent idintent=getIntent();
        identity=idintent.getIntExtra("identity",0);
        initCourse();
        addCourse=(FloatingActionButton)findViewById(R.id.addCourse);
        courseRecyclerView=(RecyclerView)findViewById(R.id.course_recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        courseRecyclerView.setLayoutManager(layoutManager);
        adapter=new CourseAdapter(courseList,identity);
        courseRecyclerView.setAdapter(adapter);

        addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Todo
                Intent intent=new Intent();
                intent.putExtra("identity",identity);
                intent.setClass(CourseListActivity.this,AddCourseActivity.class);
                startActivityForResult(intent,0);
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        switch (requestCode) {
            case 0:
                if(resultCode==RESULT_OK){
                    Course course=(Course) data.getSerializableExtra("course");
                    String name=course.getCourseName();
                    try{
                        course=addCourse(Integer.parseInt(User.getUniquePsuedoID()),name);
                    }catch (Exception e){

                    }
                    courseList.add(0,course);
                    adapter.notifyItemInserted(0);
                    courseRecyclerView.scrollToPosition(0);
                    Toast.makeText(this, "成功添加课程"+course.getCourseName(), Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    private void initCourse(){
        try{
            courseList=getCourseListByUserId(Integer.parseInt(User.getUniquePsuedoID()));
        }catch (Exception e){

        }

        if(courseList.size()>0)
            return;
        courseList.add(new Course("计算系统基础","王浩然",10));
        courseList.add(new Course("离散数学","马晓星",1));
        courseList.add(new Course("软件工程与计算I","刘钦"));
        courseList.add(new Course("数据结构与计算","伏晓"));
        courseList.add(new Course("计算机组织与结构","任桐伟",2));
        courseList.add(new Course("数据科学基础","陈振宇"));
        courseList.add(new Course("软件工程与计算II","刘钦"));
        courseList.add(new Course("互联网计算","刘峰"));
    }

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private List<Course> getCourseListByUserId(int userId){
        List<Course> newCourseList = new ArrayList<>();
        try {
            HttpUtil.sendOkHttpRequest("http://120.77.169.189:8080/api/course?userId="+userId, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    //通过runOnUiThread()方法回到主线程处理逻辑
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getBaseContext(), "加载失败",Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String responseBody = response.body().string();
                    try {
                        JSONObject jsonObjectResponse = new JSONObject(responseBody);
                        JSONArray courses = jsonObjectResponse.getJSONArray("content");
                        for(int i = 0; i < courses.length(); i ++){
                            JSONObject courseObject = courses.getJSONObject(i);
                            Course course=new Course();
                            course.setId(courseObject.getInt("id"));
                            course.setNotReadNum(courseObject.getInt("notReadNum"));
                            course.setCourseName(courseObject.getString("courseName"));
                            course.setTeacherName(courseObject.getString("teacherName"));
                            newCourseList.add(course);
                        }

                    } catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e){
            e.printStackTrace();
        }
        return newCourseList;
    }

    public Course addCourse(int userId,String name){
        Course course = new Course();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userId", userId);
            jsonObject.put("name",name);
            RequestBody requestBody = RequestBody.create(JSON, jsonObject.toString());
            HttpUtil.sendOkHttpResponse("http://120.77.169.189:8080/api/course/add", requestBody, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    //通过runOnUiThread()方法回到主线程处理逻辑
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getBaseContext(), "加载失败",Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String responseBody = response.body().string();
                    try {
                        JSONObject courseObject = new JSONObject(responseBody);
                        course.setId(courseObject.getInt("id"));
                        course.setNotReadNum(courseObject.getInt("notReadNum"));
                        course.setCourseName(courseObject.getString("courseName"));
                        course.setTeacherName(courseObject.getString("teacherName"));
                    } catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e){
            e.printStackTrace();
        }
        return course;
    }


}
