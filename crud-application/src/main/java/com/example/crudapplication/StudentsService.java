package com.example.crudapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class StudentsService {
    @Autowired
    private StudentRepository studentRepository;

    private String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}";

    public static boolean valEmail(String email, String emailRegex){
        Pattern emailPat = Pattern.compile(emailRegex,Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPat.matcher(email);
        return matcher.find();
    }

    //save student information
    public Students saveStudent(Students student){
        return studentRepository.save(student);
    }

    //fetch all students
    public List<Students> getAllStudents(){
        return studentRepository.findAll();
    }

    //fetch student by id
    public Optional<Students> getStudentById(Integer id){
        return studentRepository.findById(id);
    }

    //update student

    public Students updateStudent(Integer id, Students updatedStudent){
        return studentRepository.findById(id)
                .map(student -> {
                    student.setFname(updatedStudent.getFname());
                    student.setLname(updatedStudent.getLname());
                    student.setEmail(updatedStudent.getEmail());
                    student.setNumber(updatedStudent.getNumber());
                    student.setBirthdate(updatedStudent.getBirthdate());
                    student.setMarkssee(updatedStudent.getMarkssee());
                    student.setMarksPlus2(updatedStudent.getMarksPlus2());
                    student.setCourse(updatedStudent.getCourse());
                    student.setGender(updatedStudent.getGender());
                    return studentRepository.save(student);
                })
                .orElseThrow(() -> new RuntimeException("Student not found with id " + id));
    }

    //delete student

    public void deleteStudent(Integer id){
        if(studentRepository.existsById(id)){
            studentRepository.deleteById(id);
        }else{
            throw new RuntimeException("Student not found with id" + id);
        }
    }
}
