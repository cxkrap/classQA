package com.nju.classqa;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nju.classqa.vo.Question;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {
    private List<Question> questionList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView questionContent;
        TextView questionNum;
        TextView questionChecked;
        public ViewHolder(View view){
            super(view);
            questionContent=(TextView)view.findViewById(R.id.question_content);
            questionNum=(TextView)view.findViewById(R.id.question_num);
            questionChecked=(TextView)view.findViewById(R.id.isChecked);
        }
    }

    public QuestionAdapter(List<Question> questionList){
        this.questionList =questionList;
    }

    @Override
    public QuestionAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.question_item,parent,false);
        final QuestionAdapter.ViewHolder holder=new QuestionAdapter.ViewHolder(view);
        holder.questionContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position=holder.getAdapterPosition();
                Question question=questionList.get(position);
                holder.questionChecked.setText("已读");
                holder.questionChecked.setTextColor(R.color.colorPrimary);
                //Todo
                Intent intent=new Intent(parent.getContext(),QAActivity.class);
                intent.putExtra("questionId",question.getId());
                parent.getContext().startActivity(intent);
            }
        });
        holder.questionNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Todo
                int position=holder.getAdapterPosition();
                Question question=questionList.get(position);
                if(!question.getHasAddNum()){
                    question.addNum();
                    holder.questionNum.setText(question.getNum()+"人有疑惑");
                    holder.questionNum.setTextColor(R.color.colorAccent);
                    holder.questionChecked.setText("已读");
                    holder.questionChecked.setTextColor(R.color.colorPrimary);
                    question.setHasAddNum(true);
                }
                else {
                    holder.questionNum.setTextColor(R.color.colorPrimaryDark);
                }
            }
        });
        holder.questionChecked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.questionChecked.setText("已读");
                holder.questionChecked.setTextColor(R.color.colorPrimary);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(QuestionAdapter.ViewHolder holder, int position){
        Question question= questionList.get(position);
        holder.questionContent.setText(question.getContent());
        holder.questionNum.setText(question.getNum()+"人有疑惑");
    }

    @Override
    public int getItemCount(){
        return questionList.size();
    }
}
