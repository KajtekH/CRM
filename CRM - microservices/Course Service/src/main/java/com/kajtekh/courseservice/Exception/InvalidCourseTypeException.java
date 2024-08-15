package com.kajtekh.courseservice.Exception;

import com.kajtekh.courseservice.Model.CourseType;

public class InvalidCourseTypeException extends RuntimeException {
   public InvalidCourseTypeException(CourseType courseType){
       super("Invalid course type:" + courseType);
   }

    public InvalidCourseTypeException(String courseType){
        super("Invalid course type:" + courseType);
    }
}
