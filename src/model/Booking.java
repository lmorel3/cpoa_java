/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author laurent
 */
public class Booking {
    
    private int booking_id;
    private Date checkinDate;
    private Date checkoutDate;
    private Hotel hotel;

    public Booking(int booking_id, Date checkin_date, Date checkout_date, Hotel hotel) {
        this.booking_id = booking_id;
        this.checkinDate = checkin_date;
        this.checkoutDate = checkout_date;
        this.hotel = hotel;
    }   

    /**
     * Get the value of hotel
     *
     * @return the value of hotel
     */
    public Hotel getHotel() {
        return hotel;
    }

    /**
     * Set the value of hotel
     *
     * @param hotel new value of hotel
     */
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    /**
     * Get the value of checkout_date
     *
     * @return the value of checkout_date
     */
    public Date getCheckoutDate() {
        return checkoutDate;
    }

    /**
     * Set the value of checkout_date
     *
     * @param checkout_date new value of checkout_date
     */
    public void setCheckoutDate(Date checkout_date) {
        this.checkoutDate = checkout_date;
    }


    /**
     * Get the value of checkin_date
     *
     * @return the value of checkin_date
     */
    public Date getCheckinDate() {
        return checkinDate;
    }

    /**
     * Set the value of checkin_date
     *
     * @param checkin_date new value of checkin_date
     */
    public void setCheckinDate(Date checkin_date) {
        this.checkinDate = checkin_date;
    }

    /**
     * Get the value of booking_id
     *
     * @return the value of booking_id
     */
    public int getBookingId() {
        return booking_id;
    }

    /**
     * Set the value of booking_id
     *
     * @param booking_id new value of booking_id
     */
    public void setBookingId(int booking_id) {
        this.booking_id = booking_id;
    }

    
}
