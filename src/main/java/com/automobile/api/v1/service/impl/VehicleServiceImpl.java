package com.automobile.api.v1.service.impl;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.automobile.api.exeptions.ApiGenericException;
import com.automobile.api.exeptions.BadRequestException;
import com.automobile.api.exeptions.ConflictException;
import com.automobile.api.exeptions.NotFoundException;
import com.automobile.api.utils.ConstantExeption;
import com.automobile.api.utils.Utils;
import com.automobile.api.v1.entities.ModelView;
import com.automobile.api.v1.entities.Vehicle;
import com.automobile.api.v1.model.dto.VehicleDTO;
import com.automobile.api.v1.repositorie.ModelViewRepository;
import com.automobile.api.v1.repositorie.VehicleRepository;
import com.automobile.api.v1.service.VehicleService;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class VehicleServiceImpl implements VehicleService {

	private VehicleRepository vehicleRepository;

	private ModelViewRepository modelViewRepository;

	public VehicleServiceImpl(VehicleRepository vehicleRepository, ModelViewRepository modelViewRepository) {
		this.vehicleRepository = vehicleRepository;
		this.modelViewRepository = modelViewRepository;
	}

	/**
	 * Method to register new vehicles, receives a body data that maintains the
	 * structure of the object to be saved and returns a new vehicle type object
	 */
	public VehicleDTO save(VehicleDTO vehicleDTO) {
		try {
			Vehicle vehicle = Utils.copy(vehicleDTO, Vehicle.class);
			if (vehicle == null)
				throw new NotFoundException(ConstantExeption.INVALID_ENTITY);
			Vehicle vehicleFound = vehicleRepository.findByTuition(vehicle.getTuition());
			if (vehicleFound != null)
				throw new ConflictException(ConstantExeption.DUPLICATE_VEHICLE);
			if (vehicle.getModelId() == null || vehicle.getTuition() == null || vehicle.getTuition().isEmpty()
					|| vehicle.getColor() == null || vehicle.getColor().isEmpty())
				throw new BadRequestException(ConstantExeption.INVALID_ENTITY);
			ModelView modelViewFound = modelViewRepository.findById(vehicle.getModelId()).orElse(null);
			if (modelViewFound == null)
				throw new NotFoundException(ConstantExeption.MODEL_NOT_FOUND);
			vehicle.setCreatedAt(new Timestamp(new Date().getTime()));
			vehicle.setStatus(1);

			return Utils.copy(vehicleRepository.save(vehicle), VehicleDTO.class);
		} catch (JsonProcessingException e) {
			throw new ApiGenericException("exeption" + e.getLocalizedMessage());
		}
	}

	/**
	 * Method to update vehicles, receives a body data that maintains the
	 * structure of the object to be update and returns a updated vehicle type
	 * object
	 * *
	 */
	public VehicleDTO update(Long id, VehicleDTO vehicleDTO) {
		try {
			if (vehicleDTO == null)
				throw new NotFoundException(ConstantExeption.INVALID_ENTITY);
			Vehicle vehicleFound = vehicleRepository.findById(id).orElse(null);
			if (vehicleFound == null)
				throw new NotFoundException(ConstantExeption.VEHICLE_NOT_FOUND);
			Vehicle vehicle = Utils.copy(vehicleDTO, Vehicle.class);
			if (vehicle.getModelId() == null || vehicle.getTuition() == null || vehicle.getTuition().isEmpty()
					|| vehicle.getColor() == null || vehicle.getColor().isEmpty())
				throw new BadRequestException(ConstantExeption.INVALID_ENTITY);
			vehicle.setId(id);
			vehicle.setCreatedAt(vehicleFound.getCreatedAt());
			vehicle.setUpdatedAt(new Timestamp(new Date().getTime()));
			vehicle.setStatus(vehicleFound.getStatus());
			ModelView modelViewFound = modelViewRepository.findById(vehicle.getModelId()).orElse(null);
			if (modelViewFound == null)
				throw new NotFoundException(ConstantExeption.MODEL_NOT_FOUND);

			return Utils.copy(vehicleRepository.save(vehicle), VehicleDTO.class);
		} catch (JsonProcessingException e) {
			throw new ApiGenericException("exeption" + e.getLocalizedMessage());
		}
	}

	/**
	 * Method to logically delete a vehicle record, if the operation is successful
	 * it returns the vehicle inactivated, that is, with status 0
	 */
	public VehicleDTO delete(Long id) {
		try {
			if (id == null)
				throw new NotFoundException(ConstantExeption.INVALID_ID);
			Vehicle vehicleFound = vehicleRepository.findById(id).orElse(null);
			if (vehicleFound == null)
				throw new NotFoundException(ConstantExeption.VEHICLE_NOT_FOUND);
			vehicleFound.setUpdatedAt(new Timestamp(new Date().getTime()));
			vehicleFound.setStatus(0);

			return Utils.copy(vehicleRepository.save(vehicleFound), VehicleDTO.class);
		} catch (Exception e) {
			if (e instanceof NotFoundException) {
				throw new NotFoundException(ConstantExeption.VEHICLE_NOT_FOUND);
			} else if (e instanceof BadRequestException) {
				throw new BadRequestException(ConstantExeption.INVALID_ID);
			}
			throw new ApiGenericException("exeption" + e.getLocalizedMessage());
		}
	}

	@Override
	public VehicleDTO get(Long id) {
		try {
			Vehicle vehicleFound = vehicleRepository.findById(id).orElse(null);
			if (vehicleFound == null)
				throw new NotFoundException(ConstantExeption.VEHICLE_NOT_FOUND);
			return Utils.copy(vehicleFound, VehicleDTO.class);
		} catch (Exception e) {
			if (e instanceof NotFoundException) {
				throw new NotFoundException(ConstantExeption.VEHICLE_NOT_FOUND);
			} else if (e instanceof BadRequestException) {
				throw new BadRequestException(ConstantExeption.INVALID_ID);
			}
			throw new ApiGenericException("exeption" + e.getLocalizedMessage());
		}
	}
}
