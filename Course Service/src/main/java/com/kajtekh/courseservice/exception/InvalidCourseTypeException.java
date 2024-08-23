package com.kajtekh.courseservice.exception;

import com.kajtekh.courseservice.model.CourseType;

public class InvalidCourseTypeException extends RuntimeException {
   public InvalidCourseTypeException(CourseType courseType){
       super("Invalid course type:" + courseType);
   }

    public InvalidCourseTypeException(String courseType){
        super("Invalid course type:" + courseType);
    }
}
