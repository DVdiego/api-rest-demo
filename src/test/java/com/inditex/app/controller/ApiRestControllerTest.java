package com.inditex.app.controller;

import com.inditex.app.model.PriceBean;
import com.inditex.app.service.IRateService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.mockito.ArgumentMatchers.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ApiRestController.class )
class ApiRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    IRateService service;

    @Test
    void whenValidInput_thenReturns200() throws Exception {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-hh.mm.ss");
        final ZonedDateTime zdt = ZonedDateTime.now() ;
        final Date parsedDate = Date.from(zdt.toInstant());
        final String applyDate = dateFormat.format(parsedDate);
        final PriceBean priceBean = getPriceBean(1L, zdt);
        final List<PriceBean> priceBeanList = singletonList(priceBean);

        Mockito.when(service.getRatePrice(any(),anyLong(),anyInt())).thenReturn(priceBeanList);
        mockMvc.perform(get("/api/rate/{date}/{product}/{brand}", applyDate,"35455","1").contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("startDate", lessThanOrEqualTo(zdt.toString())))
                .andExpect(jsonPath("endDate", greaterThanOrEqualTo(zdt.toString())))
                .andExpect(jsonPath("productId").value(priceBean.getProductId()))
                .andExpect(jsonPath("brandId").value(priceBean.getBrandId()))
                .andExpect(jsonPath("price").value(Matchers.any(Double.class)));

    }

    @Test
    void whenGetRate_throwException() throws Exception {
        mockMvc.perform(get("/api/rate/{date}/{product}/{brand}","2020-06-14-18.30-00","35455","1"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void whenInvalidInput_thenReturnsError() throws Exception {
        mockMvc.perform(get("/api/rate/{id}", "0-0")).andExpect(status().isBadRequest());
    }

    @Test
    void whenListIsEmptyNoContent_testGetAll() throws Exception {
        mockMvc.perform(get("/api/rate/all")).andExpect(status().isNoContent());
    }

    private PriceBean getPriceBean(Long id, ZonedDateTime zdt) {
        return new PriceBean(id,
                1,
                Timestamp.valueOf(zdt.minusDays(1L).toLocalDateTime()),
                Timestamp.valueOf(zdt.plusDays(1L).toLocalDateTime()),
                1,
                35455L,
                0,
                BigDecimal.valueOf(35.50),
                "EUR");

    }
}