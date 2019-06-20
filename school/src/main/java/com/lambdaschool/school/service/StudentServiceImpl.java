package com.lambdaschool.school.service;

import com.lambdaschool.school.model.Student;
import com.lambdaschool.school.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "studentService")
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studrepos;

    @Override
    public List<Student> findAll(Pageable pageable) {
        List<Student> students = new ArrayList<>();
        studrepos.findAll(pageable).iterator().forEachRemaining(students::add);
        return students;
    }

    @Override
    public List<Student> findStudentByNameLike(String name, Pageable pageable) {

        return studrepos.findByStudnameContainingIgnoreCase(name, pageable);
    }

}
