package com.example.task_worker.data;

public class ReservationResponse {
    private int hotelId;
    private int reservationId;

    public ReservationResponse() {
    }

    public ReservationResponse(int hotelId, int reservationId) {
        this.hotelId = hotelId;
        this.reservationId = reservationId;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    @Override
    public String toString() {
        return "ReservationResponse{" +
                "hotelId=" + hotelId +
                ", reservationId=" + reservationId +
                '}';
    }
}
