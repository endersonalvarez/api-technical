package com.automobile.api.v1.service;

import com.automobile.api.v1.model.dto.VehicleDTO;

public interface VehicleService {

	public VehicleDTO save(VehicleDTO vehicleDTO);

	public VehicleDTO update(Long id, VehicleDTO vehicleDTO);

	public VehicleDTO delete(Long id);

	public VehicleDTO get(Long id);

}
