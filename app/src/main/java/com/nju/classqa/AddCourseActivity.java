package com.nju.classqa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddCourseActivity extends Activity {
    EditText inputText;
    Button button;
    int identity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_course);
        inputText=(EditText)findViewById(R.id.input_course);
        button=(Button)findViewById(R.id.confirm_addcourse);
        Intent inintent=getIntent();
        identity=inintent.getIntExtra("identity",0);
        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String course=inputText.getText().toString();
                if(identity==0){
                    studentPost();
                }else {
                    teacherPost();
                }
                returnData(new Course(course,"某老师"));
            }
        });
    }

    private void returnData(Course course) {
        Intent intent=new Intent();
        intent.putExtra("course", course);
        setResult(RESULT_OK,intent);
        finish();
    }

    private void teacherPost(){
        //Todo
    }

    private void studentPost(){
        //Todo
    }

}
