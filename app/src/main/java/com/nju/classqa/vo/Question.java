package com.nju.classqa.vo;

public class Question {
    private int id;
    private String content;
    private int num;

    public String getContent(){
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getNum(){
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addNum(){
        num++;
    }

}
