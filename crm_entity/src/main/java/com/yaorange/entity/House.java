package com.yaorange.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 房产
 *
 * @author ham
 */

@Entity
@Table(name = "house")
public class House implements Serializable {
    private static final long serialVersionUID = -2094736881318249012L;
    public static final Integer HOUSE = 1;
    public static final Integer STORE = 2;
    public static final Integer OFFICE = 3;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "place_area")
    private String placeArea;            // 住房位置（区）
    @Column(name = "place_street")
    private String placeStreet;            // 住房位置（街道）
    @Column(name = "place_detail")
    private String placeDetail;            // 住房位置（详情）
    @Column(name = "place_premise")
    private String placePremise;        // 住房位置（楼盘）
    /**
     * 国土性质 1:集体,2:国有
     */
    @Column(name = "land_nature")
    private Integer landNature;
    @Column(name = "construct_date")
    @JsonFormat(pattern = "yyyy")
    private String constructDate;        // 建造年代
    @Column
    private String area;                // 面积
    @Column
    private String price;                // 价格
    /**
     * 装修情况 1:精装,2:简装,3:清水
     */
    @Column(name = "decorate_case")
    private Integer decorateCase;
    /**
     * 使用情况 1:自用,2:出租
     */
    @Column(name = "user_case")
    private Integer useCase;
    @Column
    private String rental;                // 租金/月
    /**
     * 类型 1:住宅,2:公寓,3:别墅
     */
    @Column
    private Integer type;
    @Column(name = "customer_id")
    private Integer customerId;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlaceArea() {
        return placeArea;
    }

    public void setPlaceArea(String placeArea) {
        this.placeArea = placeArea;
    }

    public String getPlaceStreet() {
        return placeStreet;
    }

    public void setPlaceStreet(String placeStreet) {
        this.placeStreet = placeStreet;
    }

    public String getPlaceDetail() {
        return placeDetail;
    }

    public void setPlaceDetail(String placeDetail) {
        this.placeDetail = placeDetail;
    }

    public String getPlacePremise() {
        return placePremise;
    }

    public void setPlacePremise(String placePremise) {
        this.placePremise = placePremise;
    }

    public Integer getLandNature() {
        return landNature;
    }

    public void setLandNature(Integer landNature) {
        this.landNature = landNature;
    }

    public String getConstructDate() {
        return constructDate;
    }

    public void setConstructDate(String constructDate) {
        this.constructDate = constructDate;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getDecorateCase() {
        return decorateCase;
    }

    public void setDecorateCase(Integer decorateCase) {
        this.decorateCase = decorateCase;
    }

    public Integer getUseCase() {
        return useCase;
    }

    public void setUseCase(Integer useCase) {
        this.useCase = useCase;
    }

    public String getRental() {
        return rental;
    }

    public void setRental(String rental) {
        this.rental = rental;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


}
