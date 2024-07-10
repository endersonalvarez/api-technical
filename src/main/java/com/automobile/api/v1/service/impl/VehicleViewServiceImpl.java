package com.automobile.api.v1.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.automobile.api.utils.FilterDTO;
import com.automobile.api.utils.ResultDTO;
import com.automobile.api.utils.Utils;
import com.automobile.api.v1.entities.VehicleView;
import com.automobile.api.v1.model.dto.VehicleViewDTO;
import com.automobile.api.v1.repositorie.VehicleViewRepository;
import com.automobile.api.v1.service.VehicleViewService;

@Service
public class VehicleViewServiceImpl implements VehicleViewService {

	private VehicleViewRepository vehicleViewRepository;

	public VehicleViewServiceImpl(VehicleViewRepository vehicleViewRepository) {
		this.vehicleViewRepository = vehicleViewRepository;
	}

	@Override
	public List<VehicleViewDTO> findAll() {
		return Utils.ofList(vehicleViewRepository.findAll());
	}

	@Override
	public ResultDTO findAllCustom(FilterDTO filterDTO) {
		PageRequest pageReq = PageRequest.of(filterDTO.getPage(), filterDTO.getSize(),
				Sort.Direction.fromString(filterDTO.getSortDir()), filterDTO.getSort());
		Page<VehicleView> vehiclePages = vehicleViewRepository.findAllCustom(filterDTO.getValue(), pageReq);
		return new ResultDTO(vehiclePages.getTotalPages(), vehiclePages.getTotalElements(),
				Utils.ofList(vehiclePages.toList()));
	}
}
