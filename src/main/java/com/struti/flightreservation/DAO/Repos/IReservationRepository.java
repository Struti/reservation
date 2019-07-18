package com.struti.flightreservation.DAO.Repos;

import com.struti.flightreservation.Models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReservationRepository extends JpaRepository<Reservation,Long> {
}
