package com.nju.classqa;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nju.classqa.vo.Course;

import java.util.List;


public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder>{

    private List<Course> courseList;
    private int identity;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView courseName;
        TextView courseTeacher;
        TextView uncheckNum;
        public ViewHolder(View view){
            super(view);
            courseName=(TextView)view.findViewById(R.id.course_name);
            courseTeacher =(TextView)view.findViewById(R.id.course_teacher);
            uncheckNum=(TextView)view.findViewById(R.id.uncheck_num);
        }
    }

    public CourseAdapter(List<Course> courseList, int identity){
        this.courseList =courseList;
        this.identity=identity;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.course_item,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        if(identity==0){
            holder.courseTeacher.setVisibility(View.VISIBLE);
        }
        else {
            holder.courseTeacher.setVisibility(View.GONE);
        }
        holder.courseName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position=holder.getAdapterPosition();
                Course course=courseList.get(position);
                holder.uncheckNum.setText("");
                Intent intent=new Intent(parent.getContext(),CourseQuesActivity.class);
                intent.putExtra("identity",identity);
                intent.putExtra("courseId",course.getId());
                parent.getContext().startActivity(intent);
            }
        });
        holder.uncheckNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Todo
                holder.uncheckNum.setText("");
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        Course course= courseList.get(position);
        holder.courseName.setText(course.getName());
        holder.courseTeacher.setText(course.getTeacher());
        holder.uncheckNum.setText(course.getNum()+"");
    }

    @Override
    public int getItemCount(){
        return courseList.size();
    }

}
