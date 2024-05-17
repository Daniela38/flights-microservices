package codoacodo.vuelosapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codoacodo.vuelosapi.configuration.FlightConfiguration;
import codoacodo.vuelosapi.exceptions.ResourceNotFoundException;
import codoacodo.vuelosapi.model.Company;
import codoacodo.vuelosapi.model.Dolar;
import codoacodo.vuelosapi.model.Flight;
import codoacodo.vuelosapi.model.FlightDto;
import codoacodo.vuelosapi.repository.CompanyRepository;
import codoacodo.vuelosapi.repository.FlightRepository;
import codoacodo.vuelosapi.utils.FlightUtils;

@Service
public class FlightService {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    FlightUtils flightUtils;

    @Autowired
    FlightConfiguration flightConfiguration;

    public List<FlightDto> findAll() {
        return flightUtils.flightMapper(flightRepository.findAll(), getDolar());
    }

    public Flight createFlight(Flight flight, Long companyId) {
        Company company = companyRepository.findById(companyId)
                    .orElseThrow(() -> new ResourceNotFoundException("company", "id", companyId));
        flight.setCompany(company);
        return flightRepository.save(flight);
    }

    public FlightDto getFlightById(Long id) {
        Flight flight = flightRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("flight", "id", id));
        return flightUtils.flightToFlightDto(flight, getDolar());
    }

    public void deleteFlightById(Long id) {
        flightRepository.deleteById(id);
    }

    public Optional<Flight> updateFlight(Flight flight, Long companyId) {
        Company company = companyRepository.findById(companyId)
                    .orElseThrow(() -> new ResourceNotFoundException("company", "id", companyId));
        flight.setCompany(company);
        flightRepository.save(flight);
        return flightRepository.findById(flight.getId());
    }

    public List<Flight> getByOrigin(String origin) {
        return flightRepository.findByOrigin(origin);
    }

    public List<Flight> getByOriginDestiny(String orgin, String destiny) {
        return flightRepository.findByOriginAndDestiny(orgin, destiny);
    }

    public List<Flight> getOffers(double offerPrice) {
        List<Flight> flights = flightRepository.findAll();
        return flightUtils.detectOffers(flights, offerPrice);
    }

    private double getDolar() {
        Dolar dolar = flightConfiguration.fetchDolar();
        return dolar.getAverage();
    }

    public List<Dolar> getAllDolars() {
        return List.of(flightConfiguration.fetchAllDolars());
    }
}

/*public List<Film> getOffers(Double price) {
    List<Film> films = filmRepository.findAll();
    List<Film> offers = new ArrayList<>();
    for(Film film: films) {
        if (film.getPrice() <= price){
            offers.add(film);
        }
    }
    return offers;
}*/
