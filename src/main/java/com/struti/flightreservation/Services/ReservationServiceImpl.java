package com.struti.flightreservation.Services;

import com.struti.flightreservation.DAO.Repos.IFlightRepository;
import com.struti.flightreservation.DAO.Repos.IPassengerRepository;
import com.struti.flightreservation.DAO.Repos.IReservationRepository;
import com.struti.flightreservation.DTO.ReservationRequest;
import com.struti.flightreservation.Models.Flight;
import com.struti.flightreservation.Models.Passenger;
import com.struti.flightreservation.Models.Reservation;
import com.struti.flightreservation.Util.EmailUtil;
import com.struti.flightreservation.Util.PDFGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservationServiceImpl implements IReservationService {

    @Value("${C:\\\\Users\\\\astrutin\\\\Documents\\\\pdf\\\\reservation}")
    private String ITINERARY_DIR;

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);
    private IFlightRepository flightRepository;
    private IPassengerRepository passengerRepository;
    private IReservationRepository reservationRepository;
    private PDFGenerator pdfGenerator;
    private EmailUtil email;

    public ReservationServiceImpl(IFlightRepository flightRepository, IPassengerRepository passengerRepository,
                                  IReservationRepository reservationRepository, PDFGenerator pdfGenerator,
                                  EmailUtil email) {
        this.flightRepository = flightRepository;
        this.passengerRepository = passengerRepository;
        this.reservationRepository = reservationRepository;
        this.pdfGenerator = pdfGenerator;
        this.email = email;
    }

    @Override
    @Transactional
    public Reservation bookFlight(ReservationRequest request) {
        LOGGER.info("Inside bookFlight()");

        //Make Payment

        Long flightId = request.getFlightId();
        LOGGER.info("Fetching flight for flight id: " + flightId);
        Flight flight = flightRepository.findById(flightId).get();

        Passenger passenger = new Passenger();
        passenger.setFirstName(request.getPassengerFirstName());
        passenger.setLastName(request.getPassengerLastName());
        passenger.setEmail(request.getPassengerEmail());
        passenger.setPhone(request.getPassengerPhone());
        LOGGER.info("Saving the passenger: " + passenger);
        Passenger savedPassenger = passengerRepository.save(passenger);

        Reservation reservation = new Reservation();

        reservation.setFlight(flight);
        reservation.setPassenger(savedPassenger);
        reservation.setCheckedIn(false);
        LOGGER.info("Saving the reservation: " + reservation);
        Reservation savedReservation = reservationRepository.save(reservation);

        String filePath = ITINERARY_DIR + savedReservation.getId() + ".pdf";
        LOGGER.info("Generate the Itinerary");
        pdfGenerator.generateItnerary(savedReservation,
                filePath);
        LOGGER.info("Sending the Email with the Itinerary");
        email.sendItinerary(passenger.getEmail(), filePath);
        return savedReservation;
    }
}
