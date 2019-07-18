package com.struti.flightreservation.Services;

import com.struti.flightreservation.DTO.ReservationRequest;
import com.struti.flightreservation.Models.Reservation;

public interface IReservationService {

    Reservation bookFlight (ReservationRequest request);
}
