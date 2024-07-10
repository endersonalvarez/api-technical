package com.automobile.api.utils;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
public class FilterDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String value;
	private int page;
	private int size;
	private String sort;
	private String sortDir;
}