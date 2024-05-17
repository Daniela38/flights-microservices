package com.example.ticket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ticket.model.FlightDto;
import com.example.ticket.model.Ticket;
import com.example.ticket.model.TicketDto;
import com.example.ticket.service.FlightClient;
import com.example.ticket.service.TicketService;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    
    @Autowired
    private TicketService ticketService;

    @Autowired
    private FlightClient flightClient;

    @GetMapping("")
    public List<TicketDto> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/flights")
    public List<FlightDto> getAllFlights() {
        return flightClient.getAllFlights();
    }

    @PostMapping("/add/{flightId}")
    public Ticket addTicket(@RequestBody Ticket addedTicket, @PathVariable Long flightId) {
        return ticketService.addTicket(addedTicket, flightId);
    }

    @PutMapping("/update/{id}")
    public Ticket updateTicket(@PathVariable Long id, @RequestBody Ticket newTicket) {
        return ticketService.updateTicket(id, newTicket);
    }

    @DeleteMapping("/delete/{id}")
    public List<Ticket> deleTicket(@PathVariable Long id) {
        return ticketService.deleteTicket(id);
    }
}
