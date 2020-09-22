package com.yaorange.Controller.vo;

/**
 * @ClassNameZTreeVo
 * @Descripiotn //TODO
 * @Author luokun
 * @Date 2020/8/14 0:58
 * @Version 1.0
 **/
public class ZtreeVo {
    private Integer id;//当前节点标识id
    private Integer pId;//父节点标识id
    private String name;//节点文本
    private Boolean checked;//默认当前节点是否选中
    private Boolean open;//默认当前节点是否展开

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

}
