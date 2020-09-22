package com.yaorange.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassNameCompany
 * @Descripiotn //TODO
 * @Author luokun
 * @Date 2020/8/9 11:55
 * @Version 1.0
 **/
@Entity
@Table(name = "company")
public class Company extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column
    private String remark;
    @Column
    /**0:可用,1:不可用,2:删除*/
    private Integer state = 0;

    public Company() {
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Company(String name, String remark) {
        this.name = name;
        this.remark = remark;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
