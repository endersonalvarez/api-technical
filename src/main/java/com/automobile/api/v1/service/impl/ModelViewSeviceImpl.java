package com.automobile.api.v1.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.automobile.api.v1.entities.ModelView;
import com.automobile.api.v1.repositorie.ModelViewRepository;
import com.automobile.api.v1.service.ModelViewService;

@Service
public class ModelViewSeviceImpl implements ModelViewService {

	private ModelViewRepository modelViewRepository;

	public ModelViewSeviceImpl(ModelViewRepository modelViewRepository) {
		this.modelViewRepository = modelViewRepository;
	}

	@Override
	public List<ModelView> findAllModelByBrand() {
		return modelViewRepository.findAll();
	}

}