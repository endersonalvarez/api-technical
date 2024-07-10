package com.automobile.api.v1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.automobile.api.exeptions.BadRequestException;
import com.automobile.api.utils.ConstantExeption;
import com.automobile.api.utils.FilterDTO;
import com.automobile.api.utils.ResponseDTO;
import com.automobile.api.v1.model.dto.VehicleDTO;
import com.automobile.api.v1.service.VehicleService;
import com.automobile.api.v1.service.VehicleViewService;
import com.fasterxml.jackson.core.JsonProcessingException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

	private final VehicleService vehicleService;

	private final VehicleViewService VehicleViewService;

	public VehicleController(VehicleService vehicleService,
			com.automobile.api.v1.service.VehicleViewService vehicleViewService) {
		this.vehicleService = vehicleService;
		VehicleViewService = vehicleViewService;
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfully request"),
			@ApiResponse(responseCode = "500", description = "Internal error")
	})
	@Operation(summary = "Gets a simple list of records without filters", description = "Returns a simple list of vehicles")
	@GetMapping
	public ResponseEntity<ResponseDTO> listVehicle() {
		return ResponseDTO.ok(VehicleViewService.findAll());
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfully request"),
			@ApiResponse(responseCode = "500", description = "Internal error"),
			@ApiResponse(responseCode = "400", description = "A parameter is missing")
	})
	@Operation(summary = "Get a list of vehicles filtered by make, model or license plate", description = "Returns the vehicle records filtered and ordered by the indicated parameters")
	@GetMapping(value = "/findAllCustom")
	public ResponseEntity<ResponseDTO> listCustomVehicle(@RequestParam("value") String value,
			@RequestParam("page") int page,
			@RequestParam("size") int size,
			@RequestParam("sort") String sort,
			@RequestParam("sortDir") String sortDir) {
		if (value == null) {
			throw new BadRequestException(ConstantExeption.MANDATORY_PARAMETERS);
		}
		FilterDTO filterDTO = new FilterDTO(value, page, size, sort, sortDir);
		return ResponseDTO.ok(VehicleViewService.findAllCustom(filterDTO));
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfully request"),
			@ApiResponse(responseCode = "404", description = "Not found - The Vehicle was not found"),
			@ApiResponse(responseCode = "500", description = "Internal error"),
			@ApiResponse(responseCode = "400", description = "A parameter is missing")
	})
	@Operation(summary = "Save a new vehicle registration, there are required fields to register", description = "Return the registration of the new vehicle")
	@PostMapping
	public ResponseEntity<ResponseDTO> save(@RequestBody VehicleDTO VehicleDTO) throws JsonProcessingException {
		return ResponseDTO.ok(vehicleService.save(VehicleDTO));
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfully request"),
			@ApiResponse(responseCode = "404", description = "Not found - The Vehicle was not found"),
			@ApiResponse(responseCode = "500", description = "Internal error"),
			@ApiResponse(responseCode = "400", description = "A parameter is missing")
	})
	@Operation(summary = "Updates a vehicle record, it receives a vehicle ID as a parameter", description = "Returns the updated vehicle registration")
	@PutMapping(value = "/{id}")
	public ResponseEntity<ResponseDTO> update(@PathVariable Long id, @RequestBody VehicleDTO VehicleDTO) {
		return ResponseDTO.ok(vehicleService.update(id, VehicleDTO));
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfully request"),
			@ApiResponse(responseCode = "404", description = "Not found - The Vehicle was not found"),
			@ApiResponse(responseCode = "500", description = "Internal error"),
			@ApiResponse(responseCode = "400", description = "A parameter is missing")
	})
	@Operation(summary = "Logically deletes a vehicle record, it receives as a parameter an ID of the vehicle to be deleted and must exist", description = "Returns the record of the logically deleted vehicle with status equal to 0")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ResponseDTO> delete(@PathVariable Long id) {
		return ResponseDTO.ok(vehicleService.delete(id));
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfully request"),
			@ApiResponse(responseCode = "404", description = "Not found - The Vehicle was not found"),
			@ApiResponse(responseCode = "500", description = "Internal error"),
			@ApiResponse(responseCode = "400", description = "A parameter is missing")
	})
	@Operation(summary = "Obtains a vehicle from the ID", description = "Return a vehicle registration")
	@GetMapping(value = "/{id}")
	public ResponseEntity<ResponseDTO> get(@PathVariable Long id) {
		return ResponseDTO.ok(vehicleService.get(id));
	}
}
