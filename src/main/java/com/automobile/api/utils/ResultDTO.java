package com.automobile.api.utils;

import java.io.Serializable;
import java.util.List;

import com.automobile.api.v1.model.dto.VehicleViewDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
public class ResultDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private int page;
	private long total;
	private List<VehicleViewDTO> listVehicleViewDTOS;

}