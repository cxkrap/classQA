package com.nju.classqa.vo;

import java.io.Serializable;

public class Course implements Serializable {

    private int id;

    private String name;

    private String teacher;

    private int num;


    public Course(String name){
        this.name=name;
    }

    public Course(String name,String teacher){
        this.name=name;
        this.teacher=teacher;
    }

    public Course(String name,String teacher,int num){
        this.name=name;
        this.teacher=teacher;
        this.num=num;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }

    public String getTeacher(){
        return teacher;
    }

    public int getNum(){
        return num;
    }

    public int getId(){
        return id;
    }
}
