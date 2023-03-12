package com.inditex.app.model;

import java.util.ArrayList;
import java.util.List;

public class Prices {

    private List<PriceBean> priceBeanList;

    public List<PriceBean> getPriceBeanList() {
        if(priceBeanList == null) {
            priceBeanList = new ArrayList<>();
        }
        return priceBeanList;
    }

    public void setPriceBeanList(List<PriceBean> priceBeanList) {
        this.priceBeanList = priceBeanList;
    }
}
