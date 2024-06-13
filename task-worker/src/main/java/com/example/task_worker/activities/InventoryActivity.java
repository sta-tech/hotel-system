package com.example.task_worker.activities;

import com.example.task_worker.data.InventoryRequest;
import com.example.task_worker.data.InventoryResponse;
import com.example.task_worker.data.UndoReservationRequest;
import com.example.task_worker.data.UndoReservationResponse;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface InventoryActivity {
    @ActivityMethod
    InventoryResponse reserve(InventoryRequest reservationRequest);

    @ActivityMethod
    UndoReservationResponse undoReservation(UndoReservationRequest request);
}
