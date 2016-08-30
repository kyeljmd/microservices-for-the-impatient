package org.brightworks.friflow.admin.dto;


import org.brightworks.friflow.admin.domain.Role;

import java.util.List;

/**
 * Created by kyel on 1/3/2016.
 */
public class UserAccountDTO {

    private Long id;

    private String username;

    private String password;

    private List<Role> role;

    private NameDTO name;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }

    public NameDTO getName() {
        return name;
    }

    public void setName(NameDTO name) {
        this.name = name;
    }
}
