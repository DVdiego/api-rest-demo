package com.inditex.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Builder(toBuilder = true)
@Entity()
@Table(name = "PRICES")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class PriceBean implements Serializable {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty("brand_id")
    @Column(name = "brand_id", nullable = false)
    private Integer brandId;

    @JsonProperty("start_date")
    @Column(name = "start_date", nullable = false)
    private Timestamp startDate;

    @JsonProperty("end_date")
    @Column(name = "end_date", nullable = false)
    private Timestamp endDate;

    @JsonProperty("price_list")
    @Column(name = "price_list", nullable = false)
    private Integer priceList;

    @JsonProperty("product_id")
    @Column(name = "product_id", nullable = false)
    private Long productId;

    @JsonProperty("priority")
    @Column(name = "priority", nullable = false)
    private Integer priority;

    @JsonProperty("price")
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @JsonProperty("curr")
    @Column(name = "curr", nullable = false)
    private String curr;

    public PriceBean(Long id, Integer brandId, Timestamp startDate, Timestamp endDate, Integer priceList, Long productId, Integer priority, BigDecimal price, String curr) {
        this.id = id;
        this.brandId = brandId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceList = priceList;
        this.productId = productId;
        this.priority = priority;
        this.price = price;
        this.curr = curr;
    }

    public PriceBean() {
    }

    public Long getId() {
        return id;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public Integer getPriceList() {
        return priceList;
    }

    public Long getProductId() {
        return productId;
    }

    public Integer getPriority() {
        return priority;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCurr() {
        return curr;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public void setPriceList(Integer priceList) {
        this.priceList = priceList;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setCurr(String curr) {
        this.curr = curr;
    }
}
