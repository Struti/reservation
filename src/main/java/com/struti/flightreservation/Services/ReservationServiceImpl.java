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
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements IReservationService {

    private IFlightRepository flightRepository;
    private IPassengerRepository passengerRepository;
    private IReservationRepository reservationRepository;
    private PDFGenerator pdfGenerator;
    private EmailUtil email;

    public ReservationServiceImpl(IFlightRepository flightRepository, IPassengerRepository passengerRepository,
                                  IReservationRepository reservationRepository, PDFGenerator pdfGenerator,
                                  EmailUtil email){
        this.flightRepository = flightRepository;
        this.passengerRepository = passengerRepository;
        this.reservationRepository = reservationRepository;
        this.pdfGenerator = pdfGenerator;
        this.email = email;
    }

    @Override
    public Reservation bookFlight(ReservationRequest request) {

        //Make Payment

        Long flightId =  request.getFlightId();
        Flight flight = flightRepository.findById(flightId).get();

        Passenger passenger = new Passenger();
        passenger.setFirstName(request.getPassengerFirstName());
        passenger.setLastName(request.getPassengerLastName());
        passenger.setEmail(request.getPassengerEmail());
        passenger.setPhone(request.getPassengerPhone());

        Passenger savedPassenger = passengerRepository.save(passenger);

        Reservation reservation = new Reservation();

        reservation.setFlight(flight);
        reservation.setPassenger(savedPassenger);
        reservation.setCheckedIn(false);

        Reservation savedReservation = reservationRepository.save(reservation);

        String filePath = "C:\\Users\\astrutin\\Documents\\pdf\\reservation" + savedReservation.getId() + ".pdf";
        pdfGenerator.generateItnerary(savedReservation,
                filePath);
        email.sendItinerary(passenger.getEmail(),filePath);
        return savedReservation;
    }
}
