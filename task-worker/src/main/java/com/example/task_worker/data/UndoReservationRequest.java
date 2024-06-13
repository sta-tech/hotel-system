package com.example.task_worker.data;

import java.time.OffsetDateTime;

public class UndoReservationRequest {
    private int hotelId;
    private int roomTypeId;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;

    public UndoReservationRequest() {
    }

    public UndoReservationRequest(int hotelId, int roomTypeId, OffsetDateTime startDate, OffsetDateTime endDate) {
        this.hotelId = hotelId;
        this.roomTypeId = roomTypeId;
        this.startDate = startDate;
        this.endDate = endDate;
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

    @Override
    public String toString() {
        return "UndoReservationRequest{" +
                "hotelId=" + hotelId +
                ", roomTypeId=" + roomTypeId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
