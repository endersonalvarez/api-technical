package com.automobile.api.v1.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VehicleViewDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String tuition;
	private String color;
	private String nameBrand;
	private String nameModel;
	private String annio;
	private java.sql.Timestamp updatedAt;
	private java.sql.Timestamp createdAt;
	private Integer status;
}