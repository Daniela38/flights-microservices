package com.example.ticket.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ticket.model.FlightDto;
import com.example.ticket.model.Ticket;
import com.example.ticket.model.TicketDto;
import com.example.ticket.utils.TicketUtils;

@Service
public class TicketService {
    
    @Autowired
    TicketUtils ticketUtils;

    @Autowired
    private FlightClient flightClient;

    private final List<Ticket> tickets = new ArrayList<>();
    
    public List<TicketDto> getAllTickets() {
        return ticketUtils.ticketMapper(tickets);
    }

    public Ticket addTicket(Ticket addedTicket, Long flightId) {
        FlightDto flight = flightClient.findFlightById(flightId);
        addedTicket.setFlight(flight);
        tickets.add(addedTicket);
        return addedTicket;
    }

    public Ticket updateTicket(Long id, Ticket newTicket) {
        for(int i = 0; i < tickets.size(); i ++) {
            Ticket ticket = tickets.get(i);
            if(ticket.getId() == id) {
                tickets.set(i, newTicket);
            }
        }
        return newTicket;
    }

    public List<Ticket> deleteTicket(Long id) {
        for(int i = 0; i < tickets.size(); i ++) {
            Ticket ticket = tickets.get(i);
            if(ticket.getId() == id) {
                tickets.remove(ticket);
            }
        }
        return tickets;
    }
}
