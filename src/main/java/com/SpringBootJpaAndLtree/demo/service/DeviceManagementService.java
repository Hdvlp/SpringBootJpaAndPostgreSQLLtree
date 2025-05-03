package com.SpringBootJpaAndLtree.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.SpringBootJpaAndLtree.demo.model.DeviceEntity;
import com.SpringBootJpaAndLtree.demo.repo.DeviceRepository;

@Service
public class DeviceManagementService {

    private static DeviceRepository deviceRepository;

    DeviceManagementService (DeviceRepository deviceRepositoryR){
        deviceRepository = deviceRepositoryR;
    }

    public static List<DeviceEntity> findDeviceEntity(String[] paths){
        return deviceRepository.findByPathContaining(paths);
    } 

    public static void runFindDeviceEntity(String[] paths){
        List<DeviceEntity> devices = findDeviceEntity(paths);

		for (DeviceEntity device : devices) {
			System.out.println(device);
		}

        return;
    } 
}
