package com.erp.bean.VO;

import com.erp.bean.COrder;

import java.util.Date;

public class ManufactureVO {
    private String manufactureSn;


    private COrder cOrder;

    private String technologyId;

    private Integer launchQuantity;

    private Date beginDate;

    private Date endDate;

    public String getManufactureSn() {
        return manufactureSn;
    }

    public void setManufactureSn(String manufactureSn) {
        this.manufactureSn = manufactureSn == null ? null : manufactureSn.trim();
    }


    public String getTechnologyId() {
        return technologyId;
    }

    public void setTechnologyId(String technologyId) {
        this.technologyId = technologyId == null ? null : technologyId.trim();
    }

    public Integer getLaunchQuantity() {
        return launchQuantity;
    }

    public void setLaunchQuantity(Integer launchQuantity) {
        this.launchQuantity = launchQuantity;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public COrder getcOrder() {
        return cOrder;
    }

    public void setcOrder(COrder cOrder) {
        this.cOrder = cOrder;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}