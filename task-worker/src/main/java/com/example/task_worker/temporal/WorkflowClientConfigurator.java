package com.example.task_worker.temporal;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowClientOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WorkflowClientConfigurator {
    @Bean
    public WorkflowClient createClient() {
        return WorkflowClient.newInstance(
                WorkflowServiceStubs.newServiceStubs(
                        WorkflowServiceStubsOptions.newBuilder()
                                .build()
                ),
                WorkflowClientOptions.newBuilder()
                        .build()
        );
    }
}
