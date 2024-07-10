package com.automobile.api.utils;

import java.util.List;
import java.util.stream.Collectors;

import com.automobile.api.v1.entities.VehicleView;
import com.automobile.api.v1.model.dto.VehicleViewDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {
	public static <T> T copy(Object object, Class<T> c) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String values = mapper.writeValueAsString(object);
		return mapper.readValue(values, c);
	}

	public static List<VehicleViewDTO> ofList(List<VehicleView> entities) {
		return entities == null ? null : entities.stream().map(c -> {
			try {
				return copy(c, VehicleViewDTO.class);
			} catch (JsonProcessingException e) {
			}
			return null;
		}).collect(Collectors.toList());
	}

}
