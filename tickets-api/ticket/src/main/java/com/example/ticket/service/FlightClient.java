package com.example.ticket.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.ticket.model.FlightDto;

@FeignClient(name = "flight-service")

public interface FlightClient {
    
    @GetMapping("/flights")
    List<FlightDto> getAllFlights();

    @GetMapping("flights/{id}")
    FlightDto findFlightById(@PathVariable Long id);
}
