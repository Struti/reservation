package com.struti.flightreservation.DAO.Repos;

import com.struti.flightreservation.Models.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPassengerRepository extends JpaRepository<Passenger,Long> {
}
