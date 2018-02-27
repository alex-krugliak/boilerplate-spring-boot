package com.web.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by alex on 12.02.18.
 */
@MappedSuperclass
public class Base implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "create_date", nullable = false)
    private Date createDate = new Date();
    ;

    @Column(name = "modified_date", nullable = false)
    private Date modifiedDate = new Date();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
