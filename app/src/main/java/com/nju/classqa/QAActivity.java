package com.nju.classqa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nju.classqa.util.HttpUtil;
import com.nju.classqa.vo.Answer;
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

public class QAActivity extends Activity {
    private List<Answer> answerList=new ArrayList<>();
    private Button sendAnswer;
    private TextView aimed_Ques;
    private RecyclerView answerRecyclerView;
    private AnswerAdapter adapter;
    private int questionId;
    private String questionContent;
    private EditText inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answerlist);
        Intent inintent=getIntent();
        questionId=inintent.getIntExtra("questionId",0);
        aimed_Ques=(TextView)findViewById(R.id.aimed_question);
        questionContent=inintent.getStringExtra("questionContent");
        aimed_Ques.setText("Question: "+questionContent);
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
                    Answer answer=new Answer(content,0);;
                    try{
                        answer=addAnswer(Integer.parseInt(User.getUniquePsuedoID()), content, questionId);
                    }catch (Exception e){

                    }
                    answerList.add(0,answer);
                    adapter.notifyItemInserted(0);
                    answerRecyclerView.scrollToPosition(0);
                    inputText.setText("");
                }
            }
        });

    }

    private void initAnswer(){
        try{
            answerList=getAnswerListByQuestionId(questionId);
        }catch (Exception e){

        }

        if(answerList.size()>0)
            return;
        for(int i=0;i<10;i++){
            answerList.add(new Answer("NO"+(i+1)+":  "+"配合度：Hackathon 是一项团队赛，需要队员之间的积极配合和及时沟通，这是一场团战，参团协作很重要。切忌单打独斗。"));
        }
    }

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private List<Answer> getAnswerListByQuestionId(int questionId){
        List<Answer> newAnswerList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("question_id", questionId);
            RequestBody requestBody = RequestBody.create(JSON, jsonObject.toString());
            HttpUtil.sendOkHttpResponse("http://120.77.169.189:8080/api/", requestBody, new Callback() {
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
                        JSONArray answers = jsonObjectResponse.getJSONArray("content");
                        for(int i = 0; i < answers.length(); i ++){
                            JSONObject answerObject = answers.getJSONObject(i);
                            Answer answer=new Answer();
                            answer.setId(answerObject.getInt("id"));
                            answer.setNum(answerObject.getInt("unable_num"));
                            answer.setContent(answerObject.getString("content"));
                            newAnswerList.add(answer);
                        }

                    } catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e){
            e.printStackTrace();
        }
        return newAnswerList;
    }

    public Answer addAnswer(int userId, String content, int questionId){
        Answer answer = new Answer();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("user_id", userId);
            jsonObject.put("content", content);
            jsonObject.put("course_id", questionId);
            RequestBody requestBody = RequestBody.create(JSON, jsonObject.toString());
            HttpUtil.sendOkHttpResponse("http://120.77.169.189:8080/api/", requestBody, new Callback() {
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
                        JSONObject answerObject = new JSONObject(responseBody);
                        answer.setId(answerObject.getInt("id"));
                        answer.setNum(answerObject.getInt("unable_num"));
                        answer.setContent(answerObject.getString("content"));
                    } catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e){
            e.printStackTrace();
        }
        return answer;
    }



}
