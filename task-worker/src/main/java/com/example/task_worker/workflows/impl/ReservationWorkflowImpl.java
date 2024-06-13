package com.example.task_worker.workflows.impl;

import com.example.task_worker.activities.InventoryActivity;
import com.example.task_worker.activities.ReservationActivity;
import com.example.task_worker.data.*;
import com.example.task_worker.workflows.ReservationWorkflow;
import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.failure.ActivityFailure;
import io.temporal.workflow.Saga;
import io.temporal.workflow.Workflow;

import java.time.Duration;

public class ReservationWorkflowImpl implements ReservationWorkflow {
    private final InventoryActivity inventoryActivities =
            Workflow.newActivityStub(
                    InventoryActivity.class,
                    ActivityOptions.newBuilder()
                            .setStartToCloseTimeout(Duration.ofSeconds(2))
                            .setRetryOptions(RetryOptions.newBuilder().setMaximumAttempts(1).build())
                            .build()
            );
    private final ReservationActivity reservationActivities =
            Workflow.newActivityStub(
                    ReservationActivity.class,
                    ActivityOptions.newBuilder()
                            .setStartToCloseTimeout(Duration.ofSeconds(2))
                            .setRetryOptions(RetryOptions.newBuilder().setMaximumAttempts(1).build())
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

        Saga.Options sagaOptions = new Saga.Options.Builder().setParallelCompensation(true).build();
        Saga saga = new Saga(sagaOptions);

        try {
            var inventoryResponse = inventoryActivities.reserve(inventoryRequest);
            saga.addCompensation(inventoryActivities::undoReservation, buildUndoRequest(inventoryRequest));

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
        } catch (ActivityFailure e) {
            saga.compensate();
            throw e;
        }
    }

    private UndoReservationRequest buildUndoRequest(InventoryRequest inventoryRequest) {
        return new UndoReservationRequest(
                inventoryRequest.getHotelId(),
                inventoryRequest.getRoomTypeId(),
                inventoryRequest.getStartDate(),
                inventoryRequest.getEndDate()
        );
    }
}
