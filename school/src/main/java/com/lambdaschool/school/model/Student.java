package com.lambdaschool.school.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "Student", description = "A student")
@Entity
@Table(name = "student")
public class Student {
    @ApiModelProperty(value = "Primary key", required = true, example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long studid;

    @Size(min = 2, max = 255, message = "Must be 2 to 255 characters long")
    private String studname;

    @ManyToMany
    @JoinTable(name = "studcourses",
            joinColumns = {@JoinColumn(name = "studid")},
            inverseJoinColumns = {@JoinColumn(name = "courseid")})
    @JsonIgnoreProperties("students")
    private List<Course> courses = new ArrayList<>();

    public Student() {
    }

    public Student(String studname) {
        this.studname = studname;
    }

    public long getStudid() {
        return studid;
    }

    public void setStudid(long studid) {
        this.studid = studid;
    }

    public String getStudname() {
        return studname;
    }

    public void setStudname(String studname) {
        this.studname = studname;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
