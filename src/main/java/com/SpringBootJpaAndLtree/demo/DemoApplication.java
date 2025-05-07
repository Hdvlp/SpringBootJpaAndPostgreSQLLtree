package com.SpringBootJpaAndLtree.demo;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.SpringBootJpaAndLtree.demo.service.DeviceManagementDataTypesService;
import com.SpringBootJpaAndLtree.demo.service.DeviceManagementEntityManagerService;
import com.SpringBootJpaAndLtree.demo.service.DeviceManagementService;


@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		DeviceManagementDataTypesService.handleDataTypes();
		
		Instant start;
		Instant end;
		long timeElapsed;

		start = null;
		end = null;
		timeElapsed = 0L;

		System.out.println();
		System.out.println("Query with JpaRepository:");

		start = Instant.now();
		DeviceManagementService.runFindDeviceEntity(new String[]{"root.P"});	
		end = Instant.now();
		timeElapsed = Duration.between(start, end).toMillis();
		System.out.println(String.format("Time spent in milliseconds: %d", timeElapsed));

		start = null;
		end = null;
		timeElapsed = 0L;

		System.out.println();
		System.out.println("Query with EntityManager: ");

		start = Instant.now();
		DeviceManagementEntityManagerService.fetchDevicesNatively(new String[]{"root.P"});
		end = Instant.now();
		timeElapsed = Duration.between(start, end).toMillis();
		System.out.println(String.format("Time spent in milliseconds: %d", timeElapsed));

		start = null;
		end = null;
		timeElapsed = 0L;

		System.out.println();
		System.out.println("With PostgreSQL function, simulate unexpected type in fetching: start");
		start = Instant.now();
		DeviceManagementEntityManagerService.simulateUnexpectedTypeFetchDevicesNatively(Arrays.asList(new String[]{"root.P"}));
		end = Instant.now();
		timeElapsed = Duration.between(start, end).toMillis();
		System.out.println(String.format("Time spent in milliseconds: %d", timeElapsed));
		System.out.println("With PostgreSQL function, simulate unexpected type in fetching: end");
		
		start = null;
		end = null;
		timeElapsed = 0L;

		System.out.println();
		System.out.println("With PostgreSQL function, simulate expected type in fetching: start");
		start = Instant.now();
		DeviceManagementEntityManagerService.simulateExpectedTypeFetchDevicesNatively(new String[]{"root.P"});
		end = Instant.now();
		timeElapsed = Duration.between(start, end).toMillis();
		System.out.println(String.format("Time spent in milliseconds: %d", timeElapsed));
		System.out.println("With PostgreSQL function, simulate expected type in fetching: end");

		start = null;
		end = null;
		timeElapsed = 0L;
	}


}
