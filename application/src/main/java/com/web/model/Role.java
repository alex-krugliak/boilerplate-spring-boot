package com.web.model;

import javax.persistence.*;

/**
 * Created by alex on 12.02.18.
 */
@Entity
@Table(name = "role")
public class Role extends Base {

    @Column(name = "role_name")
    private String name;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
