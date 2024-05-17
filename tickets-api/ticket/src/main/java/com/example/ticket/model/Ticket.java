package com.example.ticket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    private Long id;
    private FlightDto flight;
    private int quantity;
    private String passengerName;
    private String passengerEmail;
    private String passengerPassport;
}
