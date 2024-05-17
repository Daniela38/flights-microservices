package codoacodo.vuelosapi.controller;

import org.springframework.web.bind.annotation.RestController;

import codoacodo.vuelosapi.model.Dolar;
import codoacodo.vuelosapi.model.Flight;
import codoacodo.vuelosapi.model.FlightDto;
import codoacodo.vuelosapi.service.FlightService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/flights")

public class FlightController {

    @Autowired
    FlightService flightService;

     @GetMapping("")
    public List<FlightDto> getAllFlights(){
        return flightService.findAll();
    }

    @PostMapping("/add")
    public Flight createFlight(@RequestBody Flight flight, @RequestParam Long companyId){
        return flightService.createFlight(flight, companyId);
    }

    @GetMapping("/{id}")
    public FlightDto findFlightById(@PathVariable Long id){
        return flightService.getFlightById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteFlight(@PathVariable Long id){
        flightService.deleteFlightById(id);
    }

    @PutMapping("/update")
    public Optional<Flight> updateFlight(@RequestBody Flight flight, @RequestParam Long companyId){
        return flightService.updateFlight(flight, companyId);
    }

    @GetMapping("/origin") 
    public List<Flight> getFlightsByOrigin(@RequestParam String origin) {
        return flightService.getByOrigin(origin);
    }
    
    @GetMapping("/origin-destiny") 
    public List<Flight> getFlightsByOriginAndDestiny(@RequestParam String origin, @RequestParam String destiny) {
        return flightService.getByOriginDestiny(origin, destiny);
    }

    @GetMapping("/offers")
    public List<Flight> getOffers() {
        Integer offerPrice = 200;
        return flightService.getOffers(offerPrice);
    }

    @GetMapping("/all-dolars")
    public List<Dolar> getAllDolars() {
        return flightService.getAllDolars();
    }
}
