package com.automobile.api.v1.service;

import java.util.List;

import com.automobile.api.v1.entities.ModelView;

public interface ModelViewService {
    public List<ModelView> findAllModelByBrand();
}
