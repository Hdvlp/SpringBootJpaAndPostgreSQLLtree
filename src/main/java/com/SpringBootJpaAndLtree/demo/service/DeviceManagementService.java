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

    public static void handleDataTypes(){

        System.out.println();

        System.out.println("What is the corresponding data type in PostgreSQL " + 
            "for some parameters in methods in JpaRepository?");
        
        String typeOfInteger = deviceRepository.findPgTypeofBy(Integer.valueOf(1));
        System.out.println("The corresponding data type of Integer.valueOf(1): " + String.valueOf(typeOfInteger));

        String typeOfIntegerArray = deviceRepository.findPgTypeofBy(new Integer[]{5, 7, 9});
        System.out.println("The corresponding data type of new Integer[]{5, 7, 9}: " + String.valueOf(typeOfIntegerArray));

        String typeOfDouble = deviceRepository.findPgTypeofBy(Double.valueOf(0.3));
        System.out.println("The corresponding data type of Double.valueOf(0.3): " + String.valueOf(typeOfDouble));

        String typeOfString = deviceRepository.findPgTypeofBy(new String("some text"));
        System.out.println("The corresponding data type of new String(\"some text\"): " + String.valueOf(typeOfString));

        String typeOfList = deviceRepository.findPgTypeofBy(List.of("root.P"));
        System.out.println("The corresponding data type of List.of(\"root.P\"): " + String.valueOf(typeOfList));

        String typeOfStringArray = deviceRepository.findPgTypeofBy(new String[]{"root.P"});
        System.out.println("The corresponding data type of new String[]{\"root.P\"}: " + String.valueOf(typeOfStringArray));
        
        
        System.out.println();

        return;

    } 



}
