package com.inditex.app.repository;


import com.inditex.app.model.PriceBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface IRepository extends JpaRepository<PriceBean, Long>  {

/*    @Query(value = "SELECT * FROM PRICES p " +
            " WHERE p.start_date <= :applyDate" +
            " and p.end_date >= :applyDate" +
            " and p.product_id = :productId" +
            " and p.brand_id = :brandId" +
            " ORDER BY p.priority DESC"
            //to get just one -> " LIMIT 1"
            , nativeQuery = true)
    PriceBean save(PriceBean priceBean);*/
    @Query(value = "SELECT * FROM PRICES p " +
                    " WHERE p.start_date <= :applyDate" +
                        " and p.end_date >= :applyDate" +
                        " and p.product_id = :productId" +
                        " and p.brand_id = :brandId" +
                    " ORDER BY p.priority DESC"
            //to get just one -> " LIMIT 1"
            , nativeQuery = true)
    List<PriceBean> findRatePrice(@Param("applyDate") Timestamp applyDate,
                                  @Param("productId") Long productId,
                                  @Param("brandId") Integer brandId);

}
