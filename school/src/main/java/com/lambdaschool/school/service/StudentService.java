package com.lambdaschool.school.service;

import com.lambdaschool.school.model.Student;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {
    List<Student> findAll(Pageable pageable);

    List<Student> findStudentByNameLike(String name, Pageable pageable);
}
