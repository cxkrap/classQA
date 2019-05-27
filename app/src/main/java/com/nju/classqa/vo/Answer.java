package com.nju.classqa.vo;

public class Answer {

    private int id;
    private String content;
    private int thumbNum;
    private boolean hasAddNum=false;

    public Answer(){
        super();
    }

    public Answer(String content){
        this.content=content;
    }

    public Answer(String content,int thumbNum){
        this.content=content;
        this.thumbNum=thumbNum;
    }

    public  String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content=content;
    }

    public int getThumbNum(){
        return thumbNum;
    }

    public void setThumbNum(int thumbNum){
        this.thumbNum=thumbNum;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    public void addNum(){
        thumbNum++;
    }

    public boolean getHasAddNum(){
        return hasAddNum;
    }

    public void setHasAddNum(boolean hasAddNum){
        this.hasAddNum=hasAddNum;
    }
}
