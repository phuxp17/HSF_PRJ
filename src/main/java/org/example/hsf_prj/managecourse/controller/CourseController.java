package org.example.hsf_prj.managecourse.controller;

import lombok.RequiredArgsConstructor;
import org.example.hsf_prj.entity.Course;
import org.example.hsf_prj.managecourse.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.example.hsf_prj.managecourse.dto.request.CourseCreationRequest;


@Controller
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public String listCourses(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        return "course/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("courseForm", new CourseCreationRequest());
        return "course/create";
    }

    @PostMapping("/create")
    public String createCourse(@ModelAttribute("courseForm") CourseCreationRequest courseForm,
                               RedirectAttributes redirect) {
        courseService.createCourse(courseForm);
        redirect.addFlashAttribute("success", "Tạo khóa học thành công");
        return "redirect:/courses";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Course course = courseService.getById(id);

        CourseCreationRequest form = new CourseCreationRequest();
        form.setTitle(course.getTitle());
        form.setDescription(course.getDescription());
        form.setFullDescription(course.getFullDescription());
        form.setThumbnail(course.getThumbnail());
        form.setPrice(course.getPrice());
        form.setTeacherId(course.getTeacher().getId());
        form.setCategoryId(course.getCategory() != null ? course.getCategory().getId() : null);

        model.addAttribute("courseForm", form);
        model.addAttribute("courseId", id);
        return "course/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateCourse(@PathVariable Long id,
                               @ModelAttribute("courseForm") CourseCreationRequest courseForm,
                               RedirectAttributes redirect) {
        courseService.updateCourse(id, courseForm);
        redirect.addFlashAttribute("success", "Cập nhật khóa học thành công");
        return "redirect:/courses";
    }

    @PostMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id, RedirectAttributes redirect) {
        courseService.deleteCourse(id);
        redirect.addFlashAttribute("success", "Xoá khóa học thành công");
        return "redirect:/courses";
    }
}
