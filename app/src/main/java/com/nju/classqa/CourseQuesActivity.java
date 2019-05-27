package com.nju.classqa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nju.classqa.util.HttpUtil;
import com.nju.classqa.vo.Question;

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

public class CourseQuesActivity extends Activity {
    private List<Question> questionList=new ArrayList<>();
    private Button sendQuestion;
    private TextView aimed_Course;
    private RecyclerView questionRecyclerView;
    private QuestionAdapter adapter;
    private EditText inputText;
    private int courseId;
    private String courseName;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionlist);
        Intent inintent=getIntent();
        courseId=inintent.getIntExtra("courseId",0);
        courseName=inintent.getStringExtra("courseName");
        aimed_Course=(TextView)findViewById(R.id.aimed_course);
        aimed_Course.setText("Course： "+courseName);
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
                    Question question;
                    question=new Question();
                    question.setContent(content);
                    try{
                        question= addQuestion(Integer.parseInt(User.getUniquePsuedoID()), content, courseId);
                    }catch (Exception e){

                    }
                    questionList.add(0,question);
                    adapter.notifyItemInserted(0);
                    questionRecyclerView.scrollToPosition(0);
                    inputText.setText("");
                }
            }
        });
    }

    private void initQuestion(){
        try{
            questionList = getQuestionListByCourseId(courseId);
        }catch (Exception e){

        }

        if(questionList.size()>0)
            return;
        Question question=new Question();
        question.setContent("时间短：比如这次的 Hackathon 就只有 36 小时，除去吃饭和休息，留给大家开发的时间已经不多了。\n" +
                "强度高：开发过程中可能遇到各种各样的问题，这些问题都需要在短时间内快速解决。\n");
        questionList.add(question);
        questionList.add(question);
        questionList.add(question);
        questionList.add(question);
        questionList.add(question);
        questionList.add(question);
        questionList.add(question);
        questionList.add(question);



    }

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private List<Question> getQuestionListByCourseId(int courseId){
        List<Question> newQuestionList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("course_id", courseId);
            RequestBody requestBody = RequestBody.create(JSON, jsonObject.toString());
            HttpUtil.sendOkHttpRequest("http://120.77.169.189:8080/api/question?courseId=" + courseId, new Callback() {
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
                        JSONArray jsonArray = new JSONArray(responseBody);
                        JSONObject jsonObjectResponse = jsonArray.getJSONObject(0);
                        JSONArray questions = jsonObjectResponse.getJSONArray("content");
                        for(int i = 0; i < questions.length(); i ++){
                            JSONObject questionObject = questions.getJSONObject(i);
                            Question question = new Question();
                            question.setId(questionObject.getInt("id"));
                            question.setUnableNum(questionObject.getInt("unableNum"));
                            question.setContent(questionObject.getString("content"));
                            newQuestionList.add(question);
                        }

                    } catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e){
            e.printStackTrace();
        }
        return newQuestionList;
    }

    public Question addQuestion(int userId, String content, int courseId){
        Question question = new Question();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userId", userId);
            jsonObject.put("content", content);
            jsonObject.put("courseId", courseId);
            RequestBody requestBody = RequestBody.create(JSON, jsonObject.toString());
            HttpUtil.sendOkHttpResponse("http://120.77.169.189:8080/api/qusetion/add", requestBody, new Callback() {
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
                        JSONArray jsonArray = new JSONArray(responseBody);
                        JSONObject questionObject = jsonArray.getJSONObject(0);
                        question.setId(questionObject.getInt("id"));
                        question.setUnableNum(questionObject.getInt("unableNum"));
                        question.setContent(questionObject.getString("content"));
                    } catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e){
            e.printStackTrace();
        }
        return question;
    }

}
