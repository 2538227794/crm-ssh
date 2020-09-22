package com.yaorange.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * 汽车
 *
 * @author ham
 */
@Entity
@Table(name = "Car")
public class Car implements Serializable {
    private static final long serialVersionUID = 5090823655958329L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String brand;                //品牌

    @Column
    private String price;                // 购入价值

    @Column(name = "buy_date")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date buyDate;                // 购入时间

    @Column(name = "loan_status")
    private String loanStatus;            // 贷款情况

    @Column(name = "refund_month")
    private String refundMonth;            // 月供

    @Column(name = "refund_date")
    private String refundDate;            // 还款时间
    @Column(name = "customer_id")
    private Integer customerId;

    public Car() {
        super();
    }

    public Car(Integer id, String brand, String price, Date buyDate,
               String loanStatus, String refundMonth, String refundDate) {
        super();
        this.id = id;
        this.brand = brand;
        this.price = price;
        this.buyDate = buyDate;
        this.loanStatus = loanStatus;
        this.refundMonth = refundMonth;
        this.refundDate = refundDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    public String getRefundMonth() {
        return refundMonth;
    }

    public void setRefundMonth(String refundMonth) {
        this.refundMonth = refundMonth;
    }

    public String getRefundDate() {
        return refundDate;
    }

    public void setRefundDate(String refundDate) {
        this.refundDate = refundDate;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}
