package org.example.controller;

import org.example.entitiy.Passport;
import org.example.entitiy.Student;
import org.example.payload.ApiResponseModel;
import org.example.payload.ReqStudent;
import org.example.payload.ResStudent;
import org.example.repository.GroupsRepository;
import org.example.repository.PassportRepository;
import org.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    GroupsRepository groupsRepository;
    @Autowired
    PassportRepository passportRepository;

    @GetMapping
    public String getStudentPage(Model model) {
        model.addAttribute("groupList", groupsRepository.findAll());
        model.addAttribute("studentList", studentRepository.findAll());

        return "student";
    }

    @ResponseBody
    @GetMapping("/editById")
    public ApiResponseModel editStudent(@RequestParam UUID id) {
        Student student = studentRepository.findById(id).get();
        ResStudent resStudent = new ResStudent(student.getId(), student.getFirstName(), student.getLastName(),
                student.getGroups().getId(), student.getPassport().getSerial(), student.getPassport().getNumber());
        return new ApiResponseModel("success", true, resStudent);
    }


    @PostMapping
    public String saveStudent(@ModelAttribute ReqStudent reqStudent) {
        if (reqStudent.getId() == null) {
            studentRepository.save(new Student(
                    reqStudent.getFirstName(),
                    reqStudent.getLastName(),
                    groupsRepository.findById(reqStudent.getGroupId()).get(),
                    passportRepository.save(new Passport(reqStudent.getSerial(), reqStudent.getNumber()))
            ));
        } else {
            Student student = studentRepository.findById(reqStudent.getId()).get();
            student.setFirstName(reqStudent.getFirstName());
            student.setLastName(reqStudent.getLastName());
            student.setGroups(groupsRepository.findById(reqStudent.getGroupId()).get());
            student.getPassport().setSerial(reqStudent.getSerial());
            student.getPassport().setNumber(reqStudent.getNumber());

            studentRepository.save(student);

        }

        return "redirect:/student";
    }

    @ResponseBody
    @DeleteMapping("{id}")
    public ApiResponseModel deleteStudent(@PathVariable UUID id) {
        try {
            studentRepository.deleteById(id);
            return new ApiResponseModel("success", true, null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponseModel("Error in deleting", false, null);
        }

    }

    @ResponseBody
    @GetMapping("/search")
    public ApiResponseModel searchAll(@RequestParam String search) {
        ArrayList<Student> studentByAllParametr = studentRepository.getStudentByAllParametrs(search);

        return new ApiResponseModel("success", true, studentByAllParametr);
    }
}
