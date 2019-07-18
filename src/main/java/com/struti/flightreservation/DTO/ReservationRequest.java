package com.struti.flightreservation.DTO;


public class ReservationRequest {

    private Long flightId;

    private String passengerFirstName;
    private String passengerLastName;
    private String passengerEmail;
    private String passengerPhone;

    private String nemOnTheCard;
    private String cardNumber;
    private String expirationDate;
    private String securityCode;

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public String getPassengerFirstName() {
        return passengerFirstName;
    }

    public void setPassengerFirstName(String passengerFirstName) {
        this.passengerFirstName = passengerFirstName;
    }

    public String getPassengerLastName() {
        return passengerLastName;
    }

    public void setPassengerLastName(String passengerLastName) {
        this.passengerLastName = passengerLastName;
    }

    public String getPassengerEmail() {
        return passengerEmail;
    }

    public void setPassengerEmail(String passengerEmail) {
        this.passengerEmail = passengerEmail;
    }

    public String getPassengerPhone() {
        return passengerPhone;
    }

    public void setPassengerPhone(String passengerPhone) {
        this.passengerPhone = passengerPhone;
    }

    public String getNemOnTheCard() {
        return nemOnTheCard;
    }

    public void setNemOnTheCard(String nemOnTheCard) {
        this.nemOnTheCard = nemOnTheCard;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    @Override
    public String toString() {
        return "Reservation Request [ flightId = " + flightId + ", passengerFirstName = " + passengerFirstName +
                ", passengerLastName = " + passengerLastName + ", passengerEmail = " + passengerEmail +
                ", passengerPhone = " + passengerPhone + ", nemOnTheCard = " + nemOnTheCard + ", cardNumber = " +
                cardNumber + ", expirationDate = " + expirationDate + ", securityCode = " + securityCode + " ]";
    }
}
