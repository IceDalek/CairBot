package ru.com.cair.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.com.cair.entity.Student;
import ru.com.cair.repository.StudentsRepository;

@Service
public class StudentService {

    @Autowired
    private StudentsRepository studentsRepository;

    public void createStudent(Student student){
        studentsRepository.save(student);
    }

}
