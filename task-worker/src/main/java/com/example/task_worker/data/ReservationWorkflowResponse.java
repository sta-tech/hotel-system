package com.example.task_worker.data;

public class ReservationWorkflowResponse {
    private int reservationId;

    public ReservationWorkflowResponse() {
    }

    public ReservationWorkflowResponse(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    @Override
    public String toString() {
        return "ReservationWorkflowResponse{" +
                "reservationId=" + reservationId +
                '}';
    }
}
