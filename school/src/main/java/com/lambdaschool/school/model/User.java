package com.lambdaschool.school.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends Auditable {
    @Id
    @GeneratedValue
    private Long userid;

    @Column(nullable = false,
            unique = true)
    private String username;

    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties("user")
    private List<UserRoles> userroles = new ArrayList<UserRoles>();

    public User() {
    }

    public User(String username, String password, List<UserRoles> userRoles) {
        this.username = username;
        setPasswordEncrypt(password);
        this.userroles = userRoles;

        for (UserRoles userRoles1 : userRoles) {
            userRoles1.setUser(this);
        }
    }

    public void setPasswordEncrypt(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        this.password = encoder.encode(password);
    }

    public Long getUserid() {
        return userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserRoles> getUserroles() {
        return userroles;
    }

    public void setUserroles(List<UserRoles> userroles) {
        this.userroles = userroles;
    }

    public List<SimpleGrantedAuthority> getAuthority() {
        ArrayList<SimpleGrantedAuthority> outList = new ArrayList<>();

        for (UserRoles userRoles1 : userroles) {
            String myRole = Role.PREFIX + userRoles1.getRole().getName().toUpperCase();
            outList.add(new SimpleGrantedAuthority(myRole));
        }

        return outList;
    }
}