package com.SpringBootJpaAndLtree.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.SpringBootJpaAndLtree.demo.model.DeviceEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Service
public class DeviceManagementEntityManagerService {

    private static EntityManager entityManager;

    public DeviceManagementEntityManagerService(EntityManager entityManager){
        DeviceManagementEntityManagerService.entityManager = entityManager;
    }

    public static void fetchDevicesNatively(String[] paths) {

        Query q = entityManager.createNativeQuery(
            "SELECT * FROM devices_resource_tree t WHERE t.path <@ ANY(CAST(:string_array_of_paths AS ltree[]))"
        );
        q.setParameter("string_array_of_paths", paths);

        @SuppressWarnings("unchecked")
        List<Object> results = q.getResultList();

        List<DeviceEntity> entities = results.stream()
        .map(row -> mapToDeviceEntity((Object[]) row)) // Implement mapping logic
        .collect(Collectors.toList());

        for (DeviceEntity device : entities) {
            System.out.println(device.toString());
        }

        return;
    }

    private static DeviceEntity mapToDeviceEntity(Object[] row) {
        DeviceEntity entity = new DeviceEntity();
        
        entity.setId((Integer) row[0]);
        entity.setName((String) row[1]);
        entity.setPath(row[2].toString());
        
        return entity;
    }

    


}
