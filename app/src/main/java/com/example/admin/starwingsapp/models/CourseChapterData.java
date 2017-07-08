package com.example.admin.starwingsapp.models;

/**
 * Created by AKASH on 07-07-2017.
 */

public class CourseChapterData {
    String course_id, course_name, no_of_chapters;

    public CourseChapterData(String course_id, String course_name, String no_of_chapters) {
        this.course_id = course_id;
        this.course_name = course_name;
        this.no_of_chapters = no_of_chapters;
    }

    public String getCourse_id() {
        return course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public String getNo_of_chapters() {
        return no_of_chapters;
    }
}
