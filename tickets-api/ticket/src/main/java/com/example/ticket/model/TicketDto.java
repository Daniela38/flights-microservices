package com.example.ticket.model;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TicketDto {
        private Long id;
        private FlightDto flight;
        private double total;
        private String passengerName;
        private String passengerEmail;
        private String passengerPassport;
}
