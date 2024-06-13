package com.example.task_worker.workflows.impl;

import com.example.task_worker.activities.InventoryActivity;
import com.example.task_worker.activities.ReservationActivity;
import com.example.task_worker.data.*;
import com.example.task_worker.workflows.ReservationWorkflow;
import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;

import java.time.Duration;

public class ReservationWorkflowImpl implements ReservationWorkflow {
    private final InventoryActivity inventoryActivities =
            Workflow.newActivityStub(
                    InventoryActivity.class,
                    ActivityOptions.newBuilder()
                            .setStartToCloseTimeout(Duration.ofSeconds(2))
                            .build()
            );
    private final ReservationActivity reservationActivities =
            Workflow.newActivityStub(
                    ReservationActivity.class,
                    ActivityOptions.newBuilder()
                            .setStartToCloseTimeout(Duration.ofSeconds(2))
                            .build()
            );

    @Override
    public ReservationWorkflowResponse reserve(ReservationWorkflowRequest request) {
        var inventoryRequest = new InventoryRequest(
                request.getHotelId(),
                request.getRoomTypeId(),
                request.getStartDate(),
                request.getEndDate()
        );

        var inventoryResponse = inventoryActivities.reserve(inventoryRequest);

        var reservationRequest = new ReservationRequest(
                request.getHotelId()
        );

        var reservationResponse = reservationActivities.create(reservationRequest);

        return new ReservationWorkflowResponse(
                reservationResponse.getReservationId(),
                inventoryResponse.getHotelId(),
                inventoryResponse.getRoomTypeId(),
                inventoryResponse.getStartDate(),
                inventoryResponse.getEndDate(),
                inventoryResponse.getReserved(),
                inventoryResponse.getAvailable()
        );
    }
}
