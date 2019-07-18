package com.struti.flightreservation.Controllers;

import com.struti.flightreservation.DAO.Repos.IFlightRepository;
import com.struti.flightreservation.DTO.ReservationRequest;
import com.struti.flightreservation.Models.Flight;
import com.struti.flightreservation.Models.Reservation;
import com.struti.flightreservation.Services.IReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReservationController {

    private IFlightRepository flightRepository;
    private IReservationService reservationService;

    public ReservationController(IFlightRepository flightRepository, IReservationService reservationService){
        this.flightRepository = flightRepository;
        this.reservationService = reservationService;
    }

    @RequestMapping("/showCompleteReservation")
    public String showCompleteReservation(@RequestParam("flightId") Long flightId, ModelMap modelMap){

        Flight flight = flightRepository.findById(flightId).get();

        modelMap.addAttribute("flight", flight);

        return "reservation/completeReservation";
    }

    @RequestMapping(value = "/completeReservation", method= RequestMethod.POST)
    public String completeReservation(ReservationRequest reservationRequest, ModelMap modelMap){

        Reservation reservation = reservationService.bookFlight(reservationRequest);

        reservationCompletedMsg(modelMap,reservation.getId());
        return"reservation/reservationConfirmation";
    }

    @RequestMapping(value = "/completeReservation")
    public String reservationCompletedMsg(ModelMap modelMap, Long id){

        String msg = "Reservation created successfully and the id is " + id;
        modelMap.addAttribute("msg",msg);

        return"reservation/reservationConfirmation";
    }

}
