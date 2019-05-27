package com.nju.classqa.vo;

public class Question {
    private int id;
    private String content;
    private int unableNum;

    public String getContent(){
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUnableNum(){
        return unableNum;
    }

    public void setUnableNum(int unableNum) {
        this.unableNum = unableNum;
    }

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addUnableNum(){
        unableNum++;
    }

}
