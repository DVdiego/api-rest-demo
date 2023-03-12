package com.inditex.app.service;

import com.inditex.app.model.PriceBean;
import com.inditex.app.repository.IRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@Component
public class RateService implements IRateService {

    @Autowired
    private IRepository crudRepository;

    @Override
    public PriceBean save(PriceBean priceBean) {
        return crudRepository.save(priceBean);
    }

    @Override
    public List<PriceBean> getAll() {
        return crudRepository.findAll();
    }

    @Override
    public PriceBean getById(Long id) {
        return crudRepository.getReferenceById(id);
    }

    @Override
    public List<PriceBean> getRatePrice(Timestamp applyDate, Long productId, Integer brandId) {
        return crudRepository.findRatePrice(applyDate, productId, brandId);
    }
}
