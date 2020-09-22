package com.yaorange.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass//标识当前类为公共实体映射类
public class BaseEntity {
    @Column(name = "create_by")
    private String createBy;
    @Column(name = "create_time")
    @CreationTimestamp // 当使用新增方法操作该实体时，自动获取系统时间赋值给当前属性
    private Date createTime;
    @Column(name = "update_by")
    private String updateBy;
    @Column(name = "update_time")
    @UpdateTimestamp // 当使用更新方法操作该实体时，自动获取系统时间赋值给当前属性
    private Date updateTime;

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
