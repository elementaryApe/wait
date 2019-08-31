package com.rongdong.model.threeDataSource;

import java.util.Date;

public class ThreeProperty {
    private String id;

    private String userId;

    private Integer hasCreditCard;

    private String house;

    private String car;

    private Integer hasSuccessLoan;

    private String credit;

    private Date createTime;

    private Date updateTime;

    private Integer isDeleted;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getHasCreditCard() {
        return hasCreditCard;
    }

    public void setHasCreditCard(Integer hasCreditCard) {
        this.hasCreditCard = hasCreditCard;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house == null ? null : house.trim();
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car == null ? null : car.trim();
    }

    public Integer getHasSuccessLoan() {
        return hasSuccessLoan;
    }

    public void setHasSuccessLoan(Integer hasSuccessLoan) {
        this.hasSuccessLoan = hasSuccessLoan;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit == null ? null : credit.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}