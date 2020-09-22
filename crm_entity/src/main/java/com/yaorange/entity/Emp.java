package com.yaorange.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description: 员工实体
 */
@Entity
@Table(name = "emp")
public class Emp extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 8706031885670417305L;
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column
    private String account;
    @Column
    private String password;
    @Column(name = "id_card")
    private String idCard;
    @Column
    private Short state;
    @Column(name = "usable_state")
    private Short usableState;
    @Column(name = "mail_state")
    private Short mailState;
    @Column
    private String address;
    @Column
    private String corent;
    @Column(name = "entry_date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date entryDate;
    @Column(name = "dismission_date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dismissionDate;
    @Column
    private String gender;
    @Column(name = "head_img")
    private String headImg;
    @Column
    private String email;
    @Column
    private String mobile;
    @Column
    private String tel;
    @Column(name = "job_no")
    private String jobNo;
    @Column(name = "contact_name")
    private String contactName;
    @Column
    private String relation;
    @Column(name = "contact_phone")
    private String contactPhone;
    @Column
    private String remark;


    //关联映射：
    //多对一关系，关联方式基于外键
    //查询员工时关联查询公司，所以不需要配置级联（级联是针对更新当前实体的同时更新关联的实体）
    //因为前后端分离开发，每次都是返回json，那么加载策略必须为FetchType.EAGER，否则会出现no session异常
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Dept dept;

    //关联角色
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "emp_role", joinColumns = @JoinColumn(name = "emp_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    @JsonIgnoreProperties({"company"})//转换JSON字符串时排除指定属性
    private List<Role> roleList = new ArrayList<Role>();

    public Emp() {
    }

    public Emp(Integer empId, String empName) {
        this.id = empId;
        this.name = empName;
    }

    public Short getMailState() {
        return mailState;
    }

    public void setMailState(Short mailState) {
        this.mailState = mailState;
    }

    public Short getUsableState() {
        return usableState;
    }

    public void setUsableState(Short usableState) {
        this.usableState = usableState;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCorent() {
        return corent;
    }

    public void setCorent(String corent) {
        this.corent = corent;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public Date getDismissionDate() {
        return dismissionDate;
    }

    public void setDismissionDate(Date dismissionDate) {
        this.dismissionDate = dismissionDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getJobNo() {
        return jobNo;
    }

    public void setJobNo(String jobNo) {
        this.jobNo = jobNo;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
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

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }


    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", idCard='" + idCard + '\'' +
                ", state=" + state +
                ", usableState=" + usableState +
                ", address='" + address + '\'' +
                ", corent='" + corent + '\'' +
                ", entryDate=" + entryDate +
                ", dismissionDate=" + dismissionDate +
                ", gender='" + gender + '\'' +
                ", headImg='" + headImg + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", tel='" + tel + '\'' +
                ", jobNo='" + jobNo + '\'' +
                ", contactName='" + contactName + '\'' +
                ", relation='" + relation + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                ", remark='" + remark + '\'' +
                ", company=" + company +
                ", dept=" + dept +
                ", roleList=" + roleList +
                '}';
    }
}
