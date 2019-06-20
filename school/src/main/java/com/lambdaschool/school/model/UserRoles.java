package com.lambdaschool.school.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "userroles")
public class UserRoles extends Auditable implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties("userroles")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "roleid")
    @JsonIgnoreProperties("userroles")
    private Role role;

    public UserRoles() {
    }

    public UserRoles(User user, Role role) {
        this.user = user;
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (getClass() != other.getClass()) return false;

        UserRoles otherUserRoles = (UserRoles) other;

        if (user != otherUserRoles.user) return false;
        return role == otherUserRoles.role;
    }

    @Override
    public int hashCode() {
        int userHashCode;
        if (user == null) {
            userHashCode = 0;
        } else {
            userHashCode = user.hashCode();
        }

        int roleHashCode;
        if (role == null) {
            roleHashCode = 0;
        } else {
            roleHashCode = role.hashCode();
        }

        return 31 * userHashCode + roleHashCode;
    }

}