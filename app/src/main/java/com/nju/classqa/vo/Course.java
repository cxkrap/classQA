package com.nju.classqa.vo;

import java.io.Serializable;

public class Course implements Serializable {

    private int id;

    private String courseName;

    private String teacherName;

    private int notReadNum;


    public Course(){
        super();
    }

    public Course(String courseName){
        this.courseName=courseName;
    }

    public Course(String courseName,String teacherName){
        this.courseName=courseName;
        this.teacherName=teacherName;
    }

    public Course(String courseName,String teacherName,int notReadNum){
        this.courseName=courseName;
        this.teacherName=teacherName;
        this.notReadNum=notReadNum;
    }

    public void setCourseName(String courseName){
        this.courseName=courseName;
    }

    public String getCourseName(){
        return courseName;
    }

    public String getTeacherName(){
        return teacherName;
    }

    public void setTeacherName(String teacherName){
        this.teacherName=teacherName;
    }

    public int getNotReadNum(){
        return notReadNum;
    }

    public void setNotReadNum(int notReadNum){
        this.notReadNum=notReadNum;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }
}
