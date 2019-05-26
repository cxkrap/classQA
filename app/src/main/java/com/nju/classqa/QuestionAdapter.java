package com.nju.classqa;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {
    private List<Question> questionList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView questionContent;
        TextView questionNum;
        public ViewHolder(View view){
            super(view);
            questionContent=(TextView)view.findViewById(R.id.question_content);
            questionNum=(TextView)view.findViewById(R.id.question_num);
        }
    }

    public QuestionAdapter(List<Question> questionList){
        this.questionList =questionList;
    }

    @Override
    public QuestionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.question_item,parent,false);
        return new QuestionAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(QuestionAdapter.ViewHolder holder, int position){
        Question question= questionList.get(position);
        holder.questionContent.setText(question.getContent());
        holder.questionNum.setText(question.getNum());
    }

    @Override
    public int getItemCount(){
        return questionList.size();
    }
}
