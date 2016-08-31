package org.brightworks.friflow.ticketing.imm.domain;

import javax.persistence.*;
import java.util.List;

/**
 * author kyel
 */
@Entity
@Table(name = "USER_ACCOUNT")
public class UserAccount extends JpaModel{

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Embedded
    private Name name;

    @Column
    @Enumerated
    @ElementCollection(targetClass = Role.class,fetch = FetchType.EAGER)
    private List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
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

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }
}
