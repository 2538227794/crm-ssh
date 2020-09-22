package com.yaorange.Controller.vo;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassNameRosterVo
 * @Descripiotn 导入客户名单Vo对象
 * @Author luokun
 * @Date 2020/8/23 10:39
 * @Version 1.0
 **/
public class RosterVo {
    /**
     * 名单id
     */
    private Integer id;
    /**
     * 名单名称
     */
    private String name;
    /**
     * 导入日期
     */
    private String datetime;
    /**
     * 导入数量
     */
    private Integer number;
    /**
     * 已分配数量
     */
    private Integer allocated;
    /**
     * 未分配数量
     */
    private Integer unallocated;

    public RosterVo() {
    }

    public RosterVo(String name, String datetime, Integer number, Integer allocated, Integer unallocated) {
        this.name = name;
        this.datetime = datetime;
        this.number = number;
        this.allocated = allocated;
        this.unallocated = unallocated;
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

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 时间戳转换成时间
        String sd = sdf.format(datetime);
        this.datetime = sd;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getAllocated() {
        return allocated;
    }


    public void setAllocated(Integer allocated) {
        this.allocated = allocated;
    }

    public Integer getUnallocated() {
        return unallocated;
    }

    public void setUnallocated(Integer unallocated) {
        this.unallocated = unallocated;
    }
}
