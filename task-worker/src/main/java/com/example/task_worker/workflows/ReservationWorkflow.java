package com.example.task_worker.workflows;

import com.example.task_worker.data.ReservationWorkflowRequest;
import com.example.task_worker.data.ReservationWorkflowResponse;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface ReservationWorkflow {
    @WorkflowMethod
    ReservationWorkflowResponse reserve(ReservationWorkflowRequest request);
}
