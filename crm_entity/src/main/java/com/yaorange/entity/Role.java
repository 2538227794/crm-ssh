package com.yaorange.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "role")
public class Role extends BaseEntity implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column
    private String remark;
    /**
     * 配置角色和公司的关联映射
     **/
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    /**
     * 配置角色和模块的关联映射
     **/
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_module", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "module_id"))
    private List<Module> moduleList = new ArrayList<Module>();

    public Role() {
    }

    public Role(String name, String remark, Company company, List<Module> moduleList) {
        this.name = name;
        this.remark = remark;
        this.company = company;
        this.moduleList = moduleList;
    }

    public List<Module> getModuleList() {
        return moduleList;
    }

    public void setModuleList(List<Module> moduleList) {
        this.moduleList = moduleList;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
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


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
