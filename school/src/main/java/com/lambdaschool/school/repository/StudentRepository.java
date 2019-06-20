package com.lambdaschool.school.repository;

import com.lambdaschool.school.model.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.ArrayList;

public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {
    ArrayList<Student> findStudentsByStudnameEquals(String name);

    ArrayList<Student> findByStudnameContainingIgnoreCase(String name, Pageable pageable);
}
