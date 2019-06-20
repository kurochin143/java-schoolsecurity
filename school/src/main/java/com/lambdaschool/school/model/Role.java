package com.lambdaschool.school.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role extends Auditable {


    public static final String PREFIX = "ROLE_";
    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";

    public static final String ROLE_ADMIN = PREFIX + ADMIN;
    public static final String ROLE_USER = PREFIX + USER;

    @Id
    @GeneratedValue
    private Long roleid;

    @Column(nullable = false,
            unique = true)
    private String name;

    @OneToMany(mappedBy = "role",
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties("role")
    private List<UserRoles> userroles = new ArrayList<>();

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public Long getRoleid() {
        return roleid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserRoles> getUserroles() {
        return userroles;
    }

    public void setUserroles(List<UserRoles> userroles) {
        this.userroles = userroles;
    }
}