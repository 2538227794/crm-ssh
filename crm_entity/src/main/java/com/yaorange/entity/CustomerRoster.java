package com.yaorange.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * 批量导入客户的名单
 */
@Entity
@Table(name = "customer_roster")
public class CustomerRoster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column(name = "add_person_id")
    private Integer addPersonId;
    @Column(name = "add_person_name")
    private String addPersonName;
    @Column(name = "date_time")
    private Date datetime;

    public CustomerRoster() {
    }


    public CustomerRoster(String name, Integer addPersonId, String addPersonName, Date datetime) {
        this.name = name;
        this.addPersonId = addPersonId;
        this.addPersonName = addPersonName;
        this.datetime = datetime;
    }


    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public Integer getAddPersonId() {
        return addPersonId;
    }

    public void setAddPersonId(Integer addPersonId) {
        this.addPersonId = addPersonId;
    }

    public String getAddPersonName() {
        return addPersonName;
    }

    public void setAddPersonName(String addPersonName) {
        this.addPersonName = addPersonName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
