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
public class VehicleDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String tuition;
	private String color;
	private String annio;
	private Long modelId;
	private java.sql.Timestamp updatedAt;
	private java.sql.Timestamp createdAt;
	private Integer status;
}