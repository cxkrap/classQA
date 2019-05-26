package com.nju.classqa;

public class Course {
    private String name;

    private String teacher;

    public Course(String name){
        this.name=name;
    }

    public Course(String name,String teacher){
        this.name=name;
        this.teacher=teacher;
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
}
