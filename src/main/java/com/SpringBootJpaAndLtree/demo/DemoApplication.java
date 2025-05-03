package com.SpringBootJpaAndLtree.demo;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.SpringBootJpaAndLtree.demo.model.DeviceEntity;
import com.SpringBootJpaAndLtree.demo.service.DeviceManagementService;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		DeviceManagementService.handleDataTypes();

        System.out.println();
        System.out.println("DeviceManagementService.findDeviceEntity(new String[]{\"root.P\"})");
        System.out.println("deviceRepository.findByPathContaining(paths)");

		List<DeviceEntity> devices = DeviceManagementService.findDeviceEntity(new String[]{"root.P"});

		for (DeviceEntity device : devices) {
			System.out.println(device);
		 }
	}

}
