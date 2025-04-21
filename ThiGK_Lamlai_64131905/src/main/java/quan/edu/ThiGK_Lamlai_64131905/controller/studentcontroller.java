package quan.edu.ThiGK_Lamlai_64131905.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import quan.edu.ThiGK_Lamlai_64131905.model.Student;

@Controller
public class studentcontroller {
    private final List<Student> students = new ArrayList<>();

    public studentcontroller() {
        students.add(new Student("1", "Nguyễn Hoàng Gia Khiêm", "1"));
        students.add(new Student("2", "Đinh Nhật Bảo", "1"));
        students.add(new Student("3", "Ngô Gia Huy", "2"));
        students.add(new Student("4", "Nguyên Nhật Cường", "2"));
        students.add(new Student("5", "Phan Văn Tài Em", "3"));
    }

    @GetMapping("/student/all")
    public String getAllStudents(ModelMap model) {
        model.addAttribute("studentList", students);
        return "studentlist";
    }

    @GetMapping("/student/new")
    public String getNewStudent(ModelMap model) {
        model.addAttribute("student", new Student());
        return "studentnew";
    }

    @PostMapping("/student/new")
    public String postNewStudent(@ModelAttribute Student student) {
        students.add(student);
        return "redirect:/student/all";
    }

    @GetMapping("/student/view/{id}")
    public String getStudentById(@PathVariable String id, ModelMap model) {
        Student getStudent = students.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElse(null);
        model.addAttribute("getStudentView", getStudent);
        return "studentview";
    }

    @GetMapping("/student/edit/{id}")
    public String getEditStudent(@PathVariable String id, ModelMap model) {
        Student getStudent = students.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElse(null);
        model.addAttribute("getStudentEdit", getStudent);
        return "studentedit"; 
    }

    @PostMapping("/student/edit/{id}")
    public String postEditStudent(@PathVariable String id, @ModelAttribute Student student) {
        Student getStudent = students.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (getStudent != null) {
            getStudent.setName(student.getName());
            getStudent.setGroupId(student.getGroupId());
        }
        return "redirect:/student/all";
    }

    @GetMapping("/student/delete/{id}")
    public String getDeleteStudent(@PathVariable String id) {
        students.removeIf(s -> s.getId().equals(id));
        return "redirect:/student/all";
    }
}
