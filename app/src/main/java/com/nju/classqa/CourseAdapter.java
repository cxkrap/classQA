package com.nju.classqa;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import java.util.List;


public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder>{

    private List<Course> courseList;
    private DialogListStyle style;
    void setStyle(DialogListStyle dialogStyle) {
        this.style = dialogStyle;
    }
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView course;
        TextView courseteacher;
        public ViewHolder(View view){
            super(view);
            course=(TextView)view.findViewById(R.id.course);
            courseteacher=(TextView)view.findViewById(R.id.course_teacher);
        }
    }

    public CourseAdapter(List<Course> courseList){
        this.courseList =courseList;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.course_item,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position=holder.getAdapterPosition();
                Course course=courseList.get(position);
                Intent intent=new Intent(parent.getContext(),CourseQuesActivity.class);
                parent.getContext().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        Course course= courseList.get(position);
        holder.course.setText(course.getName());
        holder.courseteacher.setText(course.getTeacher());
    }

    @Override
    public int getItemCount(){
        return courseList.size();
    }

}
