package com.example.task_worker.data;

import java.time.OffsetDateTime;

public class ReservationWorkflowResponse {
    private int reservationId;
    private int hotelId;
    private int roomTypeId;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    private int reserved;
    private int available;

    public ReservationWorkflowResponse() {
    }

    public ReservationWorkflowResponse(int reservationId, int hotelId, int roomTypeId, OffsetDateTime startDate, OffsetDateTime endDate, int reserved, int available) {
        this.reservationId = reservationId;
        this.hotelId = hotelId;
        this.roomTypeId = roomTypeId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reserved = reserved;
        this.available = available;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public int getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(int roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public OffsetDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(OffsetDateTime startDate) {
        this.startDate = startDate;
    }

    public OffsetDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(OffsetDateTime endDate) {
        this.endDate = endDate;
    }

    public int getReserved() {
        return reserved;
    }

    public void setReserved(int reserved) {
        this.reserved = reserved;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "ReservationWorkflowResponse{" +
                "reservationId=" + reservationId +
                ", hotelId=" + hotelId +
                ", roomTypeId=" + roomTypeId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", reserved=" + reserved +
                ", available=" + available +
                '}';
    }
}
