package com.automobile.management;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.automobile.api.v1.controller.VehicleController;
import com.automobile.api.v1.entities.Vehicle;
import com.automobile.api.v1.model.dto.VehicleDTO;
import com.automobile.api.v1.model.dto.VehicleViewDTO;
import com.automobile.api.v1.service.VehicleService;
import com.automobile.api.v1.service.VehicleViewService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(VehicleController.class)
public class VehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VehicleViewService vehicleViewService;

    @MockBean
    private VehicleService vehicleService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void testListVehicle() throws Exception {
        List<VehicleViewDTO> vehicles = new ArrayList<>();
        VehicleViewDTO vehicleView = new VehicleViewDTO();
        vehicleView.setId(1L);
        vehicleView.setTuition("JDHSJD");
        vehicleView.setColor("BLUE");
        vehicleView.setNameBrand("CHEVROLET");
        vehicleView.setNameModel("AVEO");
        vehicleView.setStatus(1);
        vehicles.add(vehicleView);

        when(vehicleViewService.findAll()).thenReturn(vehicles);

        mockMvc.perform(MockMvcRequestBuilders.get("/vehicle")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data.length()").value(1));
    }

    @Test

    void testSaveVehicle() throws Exception {
        VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setId(1L);
        vehicleDTO.setTuition("JDHSJD");
        vehicleDTO.setColor("BLUE");
        vehicleDTO.setModelId(1L);
        vehicleDTO.setStatus(1);

        when(vehicleService.save(any(VehicleDTO.class))).thenReturn(vehicleDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/vehicle")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vehicleDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").exists());
    }
}
