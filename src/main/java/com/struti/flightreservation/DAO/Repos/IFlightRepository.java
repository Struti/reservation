package com.struti.flightreservation.DAO.Repos;

import com.struti.flightreservation.Models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface IFlightRepository extends JpaRepository<Flight,Long> {

    @Query("from Flight where departureCity=:departureCity and arrivalCity=:arrivalCity" +
            " and dateOfDeparture=:dateOfDeparture")
    List<Flight> findFlights(@Param("departureCity") String from,@Param("arrivalCity")String to,
                             @Param("dateOfDeparture")Date departureDate);
}
