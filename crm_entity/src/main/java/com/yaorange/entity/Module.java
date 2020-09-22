package com.yaorange.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author SamKK
 * @version 1.0
 * @date 2020/8/12 23:11
 */
@Entity
@Table(name = "module")
public class Module extends BaseEntity {
    @Id
    private Integer id;
    @Column
    private String name;
    @Column
    private String icon;
    @Column
    private String url;
    @Column(name = "parent_id")
    private Integer parentId;
    /**
     * 0:可用，1:不可用
     */
    @Column
    private Integer state;
    @Column
    private String remark;

    public Module() {
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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
}
