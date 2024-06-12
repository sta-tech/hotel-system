package com.example.task_worker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class TaskWorkerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskWorkerApplication.class, args);
	}

}
