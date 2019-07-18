package com.struti.flightreservation.Integration;

import com.struti.flightreservation.DAO.Repos.IReservationRepository;
import com.struti.flightreservation.DTO.ReservationUpdateRequest;
import com.struti.flightreservation.Models.Reservation;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class ReservationRestController {

    private IReservationRepository reservationRepository;

    public ReservationRestController(IReservationRepository reservationRepository){
        this.reservationRepository=reservationRepository;
    }

    @RequestMapping("/reservations/{id}")
    public Reservation findReservation(@PathVariable("id") Long id){
        return reservationRepository.findById(id).get();
    }

    @RequestMapping("/reservations")
    public Reservation updateReservation(@RequestBody ReservationUpdateRequest request){
        Reservation reservation = reservationRepository.findById(request.getId()).get();
        reservation.setNumberOfBags(request.getNumberOfBags());
        reservation.setCheckedIn(request.getCheckedIn());
        return reservationRepository.save(reservation);
    }
}
