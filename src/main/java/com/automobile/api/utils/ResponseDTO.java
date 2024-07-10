package com.automobile.api.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseDTO {
	private Object status;
	private Object error;
	private Object data;

	public ResponseDTO() {
	}

	public ResponseDTO(Object status, Object error, Object data) {
		this.status = status;
		this.error = error;
		this.data = data;
	}

	public Object getStatus() {
		return status;
	}

	public void setStatus(Object status) {
		this.status = status;
	}

	public Object getError() {
		return error;
	}

	public void setError(Object error) {
		this.error = error;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public static ResponseEntity<ResponseDTO> ok(Object data) {
		ResponseDTO responseDTO = new ResponseDTO();
		responseDTO.setStatus(HttpStatus.OK);
		responseDTO.setData(data);
		return ResponseEntity.ok(responseDTO);
	}

	public static ResponseEntity<ResponseDTO> error(Exception e) {
		ResponseDTO responseDTO = new ResponseDTO();
		responseDTO.setStatus(HttpStatus.BAD_REQUEST);
		responseDTO.setError(e);
		return ResponseEntity.ok().body(responseDTO);
	}

	public static ResponseEntity<ResponseDTO> error(HttpStatus httpStatus) {
		ResponseDTO responseDTO = new ResponseDTO();
		responseDTO.setStatus(HttpStatus.BAD_REQUEST);
		responseDTO.setError(httpStatus.toString());
		return ResponseEntity.ok().body(responseDTO);
	}

	public static ResponseEntity<ResponseDTO> error(HttpStatus httpStatus, Exception e) {
		ResponseDTO responseDTO = new ResponseDTO();
		responseDTO.setStatus(HttpStatus.BAD_REQUEST);
		responseDTO.setError(httpStatus.toString());
		responseDTO.setData(e.getMessage());
		return ResponseEntity.ok().body(responseDTO);
	}
}