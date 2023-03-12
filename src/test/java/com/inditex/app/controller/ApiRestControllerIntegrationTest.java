package com.inditex.app.controller;

import com.inditex.app.RunService;
import com.inditex.app.model.RateBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = RunService.class)
class ApiRestControllerIntegrationTest {

    @Autowired
    ApiRestController apiRestController;

    private SimpleDateFormat dateFormat;

    @BeforeEach
    void setUp() {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");
    }

    @Test
    void TestGetAll() {
        var response = apiRestController.getAll();
        assertFalse(Objects.requireNonNull(response.getBody()).isEmpty());
        assertEquals(4, response.getBody().size());
    }

    @Test // Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
    void Test1() throws ParseException {

        final String applyDate = "2020-06-14-10.00.00";
        final Date date = dateFormat.parse(applyDate);
        var der = apiRestController.getRate(applyDate, "35455", "1");
        final RateBean rateBean = der.getBody();

        assertSame(HttpStatus.OK, der.getStatusCode());
        assert rateBean != null;
        assertTrue(rateBean.getStartDate().before(date));
        assertTrue(rateBean.getEndDate().after(date));
        assertEquals(35455L, rateBean.getProductId().longValue());
        assertEquals(1, rateBean.getBrandId());
    }

    @Test // Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
    void Test2() throws ParseException {

        final String applyDate = "2020-06-14-16.00.00";
        final Date date = dateFormat.parse(applyDate);
        var der = apiRestController.getRate(applyDate, "35455", "1");
        final RateBean rateBean = der.getBody();

        assertSame(HttpStatus.OK, der.getStatusCode());
        assert rateBean != null;
        assertTrue(rateBean.getStartDate().before(date));
        assertTrue(rateBean.getEndDate().after(date));
        assertEquals(35455L, rateBean.getProductId().longValue());
        assertEquals(1, rateBean.getBrandId());
    }

    @Test // Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
    void Test3() throws ParseException {

        final String applyDate = "2020-06-14-21.00.00";
        final Date date = dateFormat.parse(applyDate);
        var der = apiRestController.getRate(applyDate, "35455", "1");
        final RateBean rateBean = der.getBody();

        assertSame(HttpStatus.OK, der.getStatusCode());
        assert rateBean != null;
        assertTrue(rateBean.getStartDate().before(date));
        assertTrue(rateBean.getEndDate().after(date));
        assertEquals(35455L, rateBean.getProductId().longValue());
        assertEquals(1, rateBean.getBrandId());
    }


    @Test // Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
    void Test4() throws Exception {

        final String applyDate = "2020-06-15-10.00.00";
        final Date date = dateFormat.parse(applyDate);
        var der = apiRestController.getRate(applyDate, "35455", "1");
        final RateBean rateBean = der.getBody();

        assertSame(HttpStatus.OK, der.getStatusCode());
        assert rateBean != null;
        assertTrue(rateBean.getStartDate().before(date));
        assertTrue(rateBean.getEndDate().after(date));
        assertEquals(35455L, rateBean.getProductId().longValue());
        assertEquals(1, rateBean.getBrandId());


    }

    @Test // Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)
    void Test5() throws ParseException {

        final String applyDate = "2020-06-16-21.00.00";
        final Date date = dateFormat.parse(applyDate);
        var der = apiRestController.getRate(applyDate, "35455", "1");
        final RateBean rateBean = der.getBody();

        assertSame(HttpStatus.OK, der.getStatusCode());
        assert rateBean != null;
        assertTrue(rateBean.getStartDate().before(date));
        assertTrue(rateBean.getEndDate().after(date));
        assertEquals(35455L, rateBean.getProductId().longValue());
        assertEquals(1, rateBean.getBrandId());
    }
}