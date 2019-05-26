package com.nju.classqa;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder>{

    private List<Course> courseList;

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
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.course_item,parent,false);
        return new ViewHolder(view);
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