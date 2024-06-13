package com.example.task_worker.data;

public class ReservationRequest {
    private int hotelId;

    public ReservationRequest() {
    }

    public ReservationRequest(int hotelId) {
        this.hotelId = hotelId;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    @Override
    public String toString() {
        return "ReservationRequest{" +
                "hotelId=" + hotelId +
                '}';
    }
}
