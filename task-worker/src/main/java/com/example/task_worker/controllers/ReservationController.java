package com.example.task_worker.controllers;

import com.example.task_worker.data.ReservationWorkflowRequest;
import com.example.task_worker.data.ReservationWorkflowResponse;
import com.example.task_worker.workflows.ReservationWorkflow;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

@RestController
@RequestMapping("/api/v1/reservation")
public class ReservationController {
    @Autowired
    private WorkflowClient workflowClient;

    @PostMapping
    public ReservationWorkflowResponse create(@RequestBody ReservationWorkflowRequest request) {
        var workflow =
                workflowClient.newWorkflowStub(
                        ReservationWorkflow.class,
                        WorkflowOptions.newBuilder()
                                .setTaskQueue("main")
                                .build());

        return workflow.reserve(request);
    }
}
