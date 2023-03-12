package com.inditex.app.controller;

import com.inditex.app.model.PriceBean;
import com.inditex.app.model.RateBean;
import com.inditex.app.service.IRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

@RestController
@RequestMapping(path="/api")
public class ApiRestController {

    @Autowired
    private IRateService service;

    @GetMapping(value = "rate/{id}")
    public ResponseEntity<PriceBean> getById(@PathVariable("id") Long id) {

        try {
            return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
        } catch (RuntimeException e) {
            Logger.getGlobal().warning(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            Logger.getGlobal().warning(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "rate/all")
    public ResponseEntity<List<PriceBean>> getAll() {

        try {
            List<PriceBean> priceBeanList = service.getAll();
            if (priceBeanList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
        } catch (RuntimeException e) {
            Logger.getGlobal().warning(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            Logger.getGlobal().warning(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value = "rate/{date}/{product}/{brand}")
    @ResponseBody
    public ResponseEntity<RateBean> getRate(@PathVariable String date,
                                  @PathVariable String product,
                                  @PathVariable String brand) {

        try {
            // SimpleDateFormat is not thread-safe, so each thread will have its own SimpleDateFormat
            ThreadLocal<SimpleDateFormat> dateFormat = ThreadLocal.withInitial(
                    ()-> new SimpleDateFormat("yyyy-MM-dd-hh.mm.ss"));
            Date parsedDate = dateFormat.get().parse(date);

            final Timestamp applyDate = new Timestamp(parsedDate.getTime());
            final Long productId = Long.parseLong(product);
            final Integer brandId = Integer.valueOf(brand);
            final List<PriceBean> latePrice = service.getRatePrice(applyDate, productId, brandId);
            // if we have more results with the same priority,
            // can use other disambiguate to apply the highest priority
            final RateBean rateBean = new RateBean(latePrice.stream().findFirst().orElseThrow());
            final ResponseEntity<RateBean> responseEntity = new ResponseEntity<>(rateBean, HttpStatus.OK);

            Logger.getGlobal().info(responseEntity::toString);
            return responseEntity;
        } catch (NoSuchElementException e) {
            Logger.getGlobal().warning(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            Logger.getGlobal().warning(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            Logger.getGlobal().warning(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
