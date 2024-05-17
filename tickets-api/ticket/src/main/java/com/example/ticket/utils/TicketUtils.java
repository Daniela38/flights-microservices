package com.example.ticket.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.ticket.model.Ticket;
import com.example.ticket.model.TicketDto;

@Component
public class TicketUtils {

    public List<TicketDto> ticketMapper(List<Ticket> tickets) {
        List<TicketDto> ticketDtos = new ArrayList<>();
        for(Ticket ticket: tickets) {
            ticketDtos.add(new TicketDto(ticket.getId(), ticket.getFlight(), ticket.getQuantity() * ticket.getFlight().getConvertedPrice(), ticket.getPassengerName(), ticket.getPassengerEmail(), ticket.getPassengerPassport()));
        }
        return ticketDtos;
    }
}
