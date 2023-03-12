package com.inditex.app.service;

import com.inditex.app.model.PriceBean;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public interface IRateService {

    PriceBean save(PriceBean priceBean);

    List<PriceBean> getAll();

    PriceBean getById(Long id);

    List<PriceBean> getRatePrice(Timestamp applyDate, Long productId, Integer brandId);
}
