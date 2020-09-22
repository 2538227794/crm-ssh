package com.yaorange.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassNameDept
 * @Descripiotn //TODO
 * @Author luokun
 * @Date 2020/8/9 7:29
 * @Version 1.0
 **/
@Entity
@Table(name = "dept")
public class Dept extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column
    private Integer state = 0;
    @Column
    private String remark;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    public Dept() {
    }

    public Dept(String name, Integer state, String remark, Company company) {
        this.name = name;
        this.state = state;
        this.remark = remark;
        this.company = company;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
