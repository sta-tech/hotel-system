package com.example.task_worker.workflows.impl;

import com.example.task_worker.activities.InventoryActivity;
import com.example.task_worker.data.InventoryRequest;
import com.example.task_worker.data.ReservationWorkflowRequest;
import com.example.task_worker.data.ReservationWorkflowResponse;
import com.example.task_worker.workflows.ReservationWorkflow;
import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;

import java.time.Duration;

public class ReservationWorkflowImpl implements ReservationWorkflow {
    private final InventoryActivity activities =
            Workflow.newActivityStub(
                    InventoryActivity.class,
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

        var inventoryResponse = activities.reserve(inventoryRequest);

        return new ReservationWorkflowResponse(
                123,
                inventoryResponse.getHotelId(),
                inventoryResponse.getRoomTypeId(),
                inventoryResponse.getStartDate(),
                inventoryResponse.getEndDate(),
                inventoryResponse.getReserved(),
                inventoryResponse.getAvailable()
        );
    }
}
