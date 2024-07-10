package com.automobile.api.v1.repositorie;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.automobile.api.v1.entities.VehicleView;

@Repository
public interface VehicleViewRepository extends JpaRepository<VehicleView, Long> {

    @Query("SELECT v FROM VehicleView v WHERE  v.status=1 and (v.tuition LIKE %:value% OR v.nameBrand LIKE %:value% OR v.nameModel LIKE %:value%)")
    Page<VehicleView> findAllCustom(@Param(value = "value") String value, PageRequest pageReq);
}
