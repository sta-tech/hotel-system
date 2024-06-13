package com.example.task_worker;

import com.example.task_worker.activities.InventoryActivity;
import com.example.task_worker.activities.ReservationActivity;
import com.example.task_worker.workflows.impl.ReservationWorkflowImpl;
import io.temporal.client.WorkflowClient;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

@EnableDiscoveryClient
@SpringBootApplication
public class TaskWorkerApplication {
	@Autowired
	private WorkflowClient workflowClient;
	@Autowired
	private InventoryActivity inventoryActivity;
	@Autowired
	private ReservationActivity reservationActivity;

	public static void main(String[] args) {
		SpringApplication.run(TaskWorkerApplication.class, args);
	}

	@EventListener
	public void onApplicationEvent(ContextRefreshedEvent event) {
		var factory = createWorkerFactory();
		factory.start();
	}

	private WorkerFactory createWorkerFactory() {
		WorkerFactory factory = WorkerFactory.newInstance(workflowClient);
		Worker worker = factory.newWorker("main");
		worker.registerWorkflowImplementationTypes(ReservationWorkflowImpl.class);
		worker.registerActivitiesImplementations(inventoryActivity);
		worker.registerActivitiesImplementations(reservationActivity);
		return factory;
	}
}
