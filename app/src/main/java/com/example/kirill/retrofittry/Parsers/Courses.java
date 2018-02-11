package com.example.kirill.retrofittry.Parsers;

import java.util.List;

import com.example.kirill.retrofittry.Parsers.Course;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Courses {

    @SerializedName("courses")
    @Expose
    private List<Course> courses = null;

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

}