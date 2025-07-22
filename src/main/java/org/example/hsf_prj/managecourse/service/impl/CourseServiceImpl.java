package org.example.hsf_prj.managecourse.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.hsf_prj.entity.*;
import org.example.hsf_prj.managecourse.dto.request.*;
import org.example.hsf_prj.managecourse.repository.*;
import org.example.hsf_prj.managecourse.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final LessonRepository lessonRepository;
    private final LessonContentRepository contentRepository;
    private final QuizRepository quizRepository;
    private final QuizQuestionRepository questionRepository;
    private final QuizAnswerRepository answerRepository;

    //  CREATE
    @Override
    public Course createCourse(CourseCreationRequest request) {
        Course course = Course.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .fullDescription(request.getFullDescription())
                .thumbnail(request.getThumbnail())
                .price(request.getPrice())
                .teacher(User.builder().id(request.getTeacherId()).build())
                .category(Category.builder().id(request.getCategoryId()).build())
                .build();

        course = courseRepository.save(course);

        // Lessons + content + quiz
        if (request.getLessons() != null) {
            for (LessonCreationRequest lessonReq : request.getLessons()) {
                Lesson lesson = Lesson.builder()
                        .title(lessonReq.getTitle())
                        .description(lessonReq.getDescription())
                        .orderIndex(lessonReq.getOrderIndex())
                        .duration(lessonReq.getDuration())
                        .type(lessonReq.getType())
                        .course(course)
                        .build();
                lesson = lessonRepository.save(lesson);

                if (lessonReq.getContents() != null) {
                    for (LessonContentRequest c : lessonReq.getContents()) {
                        LessonContent content = LessonContent.builder()
                                .lesson(lesson)
                                .title(c.getTitle())
                                .type(c.getType())
                                .content(c.getContent())
                                .fileUrl(c.getFileUrl())
                                .orderIndex(c.getOrderIndex())
                                .build();
                        contentRepository.save(content);
                    }
                }

                if (lessonReq.getQuiz() != null) {
                    QuizCreationRequest quizReq = lessonReq.getQuiz();
                    Quiz quiz = Quiz.builder()
                            .title(quizReq.getTitle())
                            .description(quizReq.getDescription())
                            .timeLimit(quizReq.getTimeLimit())
                            .maxAttempts(quizReq.getMaxAttempts())
                            .passingScore(quizReq.getPassingScore())
                            .course(course)
                            .lesson(lesson)
                            .build();
                    quiz = quizRepository.save(quiz);

                    for (QuizQuestionRequest q : quizReq.getQuestions()) {
                        QuizQuestion question = QuizQuestion.builder()
                                .quiz(quiz)
                                .question(q.getQuestion())
                                .type(q.getType())
                                .points(q.getPoints())
                                .orderIndex(q.getOrderIndex())
                                .build();
                        question = questionRepository.save(question);

                        for (QuizAnswerRequest a : q.getAnswers()) {
                            QuizAnswer answer = QuizAnswer.builder()
                                    .question(question)
                                    .answerText(a.getAnswerText())
                                    .isCorrect(a.getIsCorrect())
                                    .orderIndex(a.getOrderIndex())
                                    .build();
                            answerRepository.save(answer);
                        }
                    }
                }
            }
        }

        return course;
    }

    //  READ ALL
    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    //  READ BY ID
    @Override
    public Course getById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khóa học với ID = " + id));
    }

    //  UPDATE (cơ bản: thông tin Course; không update lesson/quiz ở đây)
    @Override
    public void updateCourse(Long id, CourseCreationRequest request) {
        Course course = getById(id);
        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setFullDescription(request.getFullDescription());
        course.setThumbnail(request.getThumbnail());
        course.setPrice(request.getPrice());
        course.setTeacher(User.builder().id(request.getTeacherId()).build());
        course.setCategory(Category.builder().id(request.getCategoryId()).build());

        courseRepository.save(course);
    }


    @Override
    public void deleteCourse(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new RuntimeException("Không tồn tại khóa học để xoá");
        }
        courseRepository.deleteById(id);
    }



}


