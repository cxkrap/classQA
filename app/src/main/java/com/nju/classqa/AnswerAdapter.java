package com.nju.classqa;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.ViewHolder> {
    private List<Answer> answerList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView answerContent;
        TextView answerNum;
        public ViewHolder(View view){
            super(view);
            answerContent=(TextView)view.findViewById(R.id.answer_content);
            answerNum =(TextView)view.findViewById(R.id.answer_num);
        }
    }

    public AnswerAdapter(List<Answer> answerlist){
        this.answerList =answerlist;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.answer_item,parent,false);
        final AnswerAdapter.ViewHolder holder=new AnswerAdapter.ViewHolder(view);
        holder.answerContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(AnswerAdapter.ViewHolder holder, int position){
        Answer answer= answerList.get(position);
        holder.answerContent.setText(answer.getContent());
        holder.answerNum.setText(answer.getNum()+"");
    }

    @Override
    public int getItemCount(){
        return answerList.size();
    }

}
