package com.inditex.app.service;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;

import com.inditex.app.model.PriceBean;
import com.inditex.app.repository.IRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@WebMvcTest(controllers = RateService.class )
class RateServiceTest {

    @MockBean
    IRateService service;

    @Test
    void save() {
        final PriceBean priceBean = getPriceBean(10L);
        Mockito.when(service.save(any(PriceBean.class))).thenReturn(priceBean);
        final PriceBean savedBean = service.save(priceBean);
        assertEquals(savedBean, priceBean);
    }

    @Test
    void getAll() {
        final List<PriceBean> list = new ArrayList<>();
        list.add(getPriceBean(1L));
        list.add(getPriceBean(2L));
        list.add(getPriceBean(3L));
        list.add(getPriceBean(4L));
        list.add(getPriceBean(5L));
        Mockito.when(service.getAll()).thenReturn(list);
        final List<PriceBean> priceBeanList = service.getAll();
        assertEquals(list, priceBeanList);
    }

    @Test
    void getById() {
        final PriceBean priceBean = getPriceBean(10L);
        Mockito.when(service.getById(anyLong())).thenReturn(priceBean);
        final PriceBean savedBean = service.getById(10L);
        assertEquals(savedBean.getId(), priceBean.getId());
    }

    @Test
    void getLatePrice() {
        final PriceBean priceBean = getPriceBean(10L);
        final List<PriceBean> list = singletonList(priceBean);

        Mockito.when(service.getRatePrice(any(),anyLong(),anyInt())).thenReturn(list);
        final List<PriceBean> priceBeanList = service.getRatePrice(priceBean.getStartDate(),
                priceBean.getProductId(),
                priceBean.getBrandId());
        assertEquals(list, priceBeanList);
    }

    private PriceBean getPriceBean(Long id) {
        return new PriceBean(id,
                1,
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()),
                1,
                35455L,
                0,
                BigDecimal.valueOf(35.50),
                "EUR");

    }
}