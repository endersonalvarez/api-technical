package com.automobile.api.v1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.automobile.api.utils.ResponseDTO;
import com.automobile.api.v1.service.ModelViewService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/model/vehicle")
public class ModelController {

	private final ModelViewService modelViewService;

	public ModelController(ModelViewService modelViewService) {
		this.modelViewService = modelViewService;
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfully request"),
			@ApiResponse(responseCode = "500", description = "Internal error")
	})
	@Operation(summary = "Gets a simple list of records without filters", description = "Returns a list of the makes and models registered in the application")
	@GetMapping
	public ResponseEntity<ResponseDTO> listModel() {
		return ResponseDTO.ok(modelViewService.findAllModelByBrand());
	}

}
