package com.nju.classqa.vo;

public class Answer {

    private int id;
    private String content;
    private int num;
    private boolean hasAddNum=false;

    public Answer(){
        super();
    }

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

    public void setContent(String content){
        this.content=content;
    }

    public int getNum(){
        return num;
    }

    public void setNum(int num){
        this.num=num;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
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
