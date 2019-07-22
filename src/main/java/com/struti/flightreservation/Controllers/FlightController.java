package com.struti.flightreservation.Controllers;


import com.struti.flightreservation.DAO.Repos.IFlightRepository;
import com.struti.flightreservation.Models.Flight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class FlightController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlightController.class);
    private IFlightRepository flightRepository;

    public FlightController(IFlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @RequestMapping("/findFlights")
    public String findFlights(@RequestParam("from") String from, @RequestParam("to") String to,
                              @RequestParam("departureDate") @DateTimeFormat(pattern = "yyyy-MM-dd")
                                      Date departureDate, ModelMap modelMap) {

        LOGGER.info("Inside findFlights() From: " + from + ", To: " + to + ", Departure Date: " + departureDate);
        List<Flight> flights = flightRepository.findFlights(from, to, departureDate);

        modelMap.addAttribute("flights", flights);
        LOGGER.info("Flights found are: " + flights);

        return "flights/displayFlights";
    }

    @RequestMapping("admin/showAddFlight")
    public String showAddFlight() {

        return "flights/addFlight";
    }
}
