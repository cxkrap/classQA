package com.nju.classqa;

public class Answer {

    private int id;
    private String content;
    private int num;
    private boolean hasAddNum=false;

    public Answer(String content){
        this.content=content;
    }

    public Answer(String content,int num){
        this.content=content;
        this.num=num;
    }

    public  String getContent(){
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
