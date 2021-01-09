package com.rameezsajid.beautefactory;

public class Booking {

    String bookingID;
    String bookingLocation;
    String bookingDate;
    String bookingName;
    String bookingPhone;
    String bookingEmail;
    String bookingBusiness;

    public Booking() {
    }

    public Booking(String bookingID, String bookingLocation, String bookingDate, String bookingName, String bookingPhone, String bookingEmail, String bookingBusiness) {
        this.bookingID = bookingID;
        this.bookingLocation = bookingLocation;
        this.bookingDate = bookingDate;
        this.bookingName = bookingName;
        this.bookingPhone = bookingPhone;
        this.bookingEmail = bookingEmail;
        this.bookingBusiness = bookingBusiness;
    }

    public String getBookingID() {
        return bookingID;
    }

    public String getBookingLocation() {
        return bookingLocation;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public String getBookingName() {
        return bookingName;
    }

    public String getBookingPhone() {
        return bookingPhone;
    }

    public String getBookingEmail() {
        return bookingEmail;
    }

    public String getBookingBusiness() {
        return bookingBusiness;
    }
}
