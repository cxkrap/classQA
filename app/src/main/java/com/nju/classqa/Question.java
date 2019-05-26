package com.nju.classqa;

public class Question {
    private int id;
    private String content;
    private int num;
    private boolean hasAddNum=false;

    public Question(String content){
        this.content=content;
    }

    public Question(String content,int num){
        this.content=content;
        this.num=num;
    }

    public String getContent(){
        return content;
    }

    public int getNum(){
        return num;
    }

    public int getId(){
        return id;
    }

    public void addNum(){
        num++;
    }

    public boolean getHasAddNum(){
        return hasAddNum;
    }

    public void setHasAddNum(boolean hasAddNum){
        this.hasAddNum=hasAddNum;
    }

}
