package com.example.task_worker.activities;

import com.example.task_worker.data.ReservationRequest;
import com.example.task_worker.data.ReservationResponse;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface ReservationActivity {
    @ActivityMethod
    ReservationResponse create(ReservationRequest request);
}
