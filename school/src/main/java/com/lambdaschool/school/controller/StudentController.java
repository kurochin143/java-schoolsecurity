package com.lambdaschool.school.controller;

import com.lambdaschool.school.model.Student;
import com.lambdaschool.school.service.StudentService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @ApiOperation(value = "return all students", response = Student.class, responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")})
    @GetMapping("/students/students")
    public ResponseEntity<?> listAllStudents(@PageableDefault(page = 0, size = 5) Pageable pageable) {
        List<Student> students = studentService.findAll(pageable);

        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @ApiOperation(value = "return all students with name like", response = Student.class, responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")})
    @GetMapping("/students/namelike/{name}")
    public ResponseEntity<?> listAllStudentsByName(@PathVariable("name") String name, @PageableDefault(page = 0, size = 5) Pageable pageable) {
        List<Student> students = studentService.findStudentByNameLike(name, pageable);

        return new ResponseEntity<>(students, HttpStatus.OK);
    }

//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "student found", response = Student.class),
//            @ApiResponse(code = 404, message = "student not found")
//    })

}
