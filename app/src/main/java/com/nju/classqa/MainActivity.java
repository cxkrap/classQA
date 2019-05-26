package com.nju.classqa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    Button button1;
    Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1=(Button) findViewById(R.id.student);
        button2=(Button)findViewById(R.id.teacher);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.putExtra("identity",0);//学生身份属性为0
                intent.setClass(MainActivity.this,CourseListActivity.class);
                startActivityForResult(intent,0);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.putExtra("identity",1);//教师身份属性为1
                intent.setClass(MainActivity.this,CourseListActivity.class);
                startActivityForResult(intent,1);
            }
        });
    }


}
