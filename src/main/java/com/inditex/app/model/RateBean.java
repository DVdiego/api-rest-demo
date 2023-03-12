package com.inditex.app.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class RateBean {

    @JsonProperty("productId")
    private Long productId;

    @JsonProperty("brandId")
    private Integer brandId;

    @JsonProperty("rateApply")
    private String rateApply;

    @JsonProperty("startDate")
    private Timestamp startDate;

    @JsonProperty("endDate")
    private Timestamp endDate;

    @JsonProperty("price")
    // You should never use float and double for monetary operations
    private BigDecimal price;

    @JsonProperty("curr")
    private String curr;

    public RateBean(PriceBean priceBean) {
        this.productId = priceBean.getProductId();
        this.brandId = priceBean.getBrandId();
        final RateName rate = RateName.get(priceBean.getPriceList());
        this.rateApply = rate.name();
        this.startDate = priceBean.getStartDate();
        this.endDate = priceBean.getEndDate();
        this.price = priceBean.getPrice();
        this.curr = priceBean.getCurr();
    }

    public Long getProductId() {
        return productId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public String getRateApply() {
        return rateApply;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCurr() {
        return curr;
    }

    @Override
    public String toString() {
        return "RateBean{" +
                "productId=" + productId +
                ", brandId=" + brandId +
                ", rateApply='" + rateApply + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", price=" + price +
                ", curr='" + curr + '\'' +
                '}';
    }
}
