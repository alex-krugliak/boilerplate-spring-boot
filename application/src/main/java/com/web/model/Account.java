package com.web.model;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by alex on 12.02.18.
 */
@Entity
@Table(name = "account")
@AttributeOverride(name = "id", column = @Column(name = "account_id"))
public class Account extends Base {

    private String sfId;

    private BigInteger pointsAmount;

    @ManyToMany
    @JoinTable(name = "user_account",
            joinColumns = {@JoinColumn(name = "account_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private List<User> users;

    public String getSfId() {
        return sfId;
    }

    public void setSfId(String sfId) {
        this.sfId = sfId;
    }

    public BigInteger getPointsAmount() {
        return pointsAmount;
    }

    public void setPointsAmount(BigInteger pointsAmount) {
        this.pointsAmount = pointsAmount;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
