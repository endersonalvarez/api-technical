package com.automobile.api.v1.service;

import java.util.List;

import com.automobile.api.utils.FilterDTO;
import com.automobile.api.utils.ResultDTO;
import com.automobile.api.v1.model.dto.VehicleViewDTO;

public interface VehicleViewService {

	public List<VehicleViewDTO> findAll();

	public ResultDTO findAllCustom(FilterDTO filterDTO);

}
