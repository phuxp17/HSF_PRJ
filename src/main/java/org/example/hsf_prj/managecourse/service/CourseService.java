package org.example.hsf_prj.managecourse.service;

import org.example.hsf_prj.entity.Course;
import org.example.hsf_prj.managecourse.dto.request.CourseCreationRequest;

import java.util.List;

public interface CourseService {
    Course createCourse(CourseCreationRequest request);
    List<Course> getAllCourses();
    Course getById(Long id);
    void updateCourse(Long id, CourseCreationRequest request);
    void deleteCourse(Long id);
}

