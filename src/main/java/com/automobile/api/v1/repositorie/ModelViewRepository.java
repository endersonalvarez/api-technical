package com.automobile.api.v1.repositorie;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.automobile.api.v1.entities.ModelView;

@Repository
public interface ModelViewRepository extends JpaRepository<ModelView, Long> {
    @Query("SELECT m FROM ModelView m WHERE  m.status=1")
    List<ModelView> findAllModelByBrand();
}
