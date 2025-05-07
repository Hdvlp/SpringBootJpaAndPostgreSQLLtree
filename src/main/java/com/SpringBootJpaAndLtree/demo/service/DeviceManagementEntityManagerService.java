package com.SpringBootJpaAndLtree.demo.service;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.stereotype.Service;

import com.SpringBootJpaAndLtree.demo.model.DeviceEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Service
public class DeviceManagementEntityManagerService {

    private static EntityManager entityManager;
    private static DataSource dataSource;

    public DeviceManagementEntityManagerService(EntityManager entityManager,
        DataSource dataSource){
        DeviceManagementEntityManagerService.entityManager = entityManager;
        DeviceManagementEntityManagerService.dataSource = dataSource;
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


    public static void createFunction() {
        String sql = """
            CREATE OR REPLACE FUNCTION get_devices_resource_tree(_paths text[])
            RETURNS TABLE (path ltree) AS
            $$
            BEGIN
                RETURN QUERY
                SELECT t.path FROM devices_resource_tree t
                WHERE t.path <@ ANY(CAST(_paths AS ltree[]));
            END;
            $$ LANGUAGE plpgsql;
        """;

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void simulateUnexpectedTypeFetchDevicesNatively(List<String> paths) {

        try{
            createFunction();
        
            String queryStr = "SELECT * FROM get_devices_resource_tree(:list_of_paths)";

            Query q = entityManager.createNativeQuery(
                queryStr
            );
            q.setParameter("list_of_paths", paths);
            var fetchedResultList = q.getResultList();
            System.out.println(fetchedResultList);

            if (Objects.equals(fetchedResultList, null) || fetchedResultList.size() == 0){
                return;
            }

            @SuppressWarnings("unchecked")
            List<Object> results = q.getResultList();

            List<DeviceEntity> entities = results.stream()
            .map(row -> mapToDeviceEntity((Object[]) row)) // Implement mapping logic
            .collect(Collectors.toList());

            for (DeviceEntity device : entities) {
                System.out.println(device.toString());
            }

        }catch(Exception e){}

        return;
    }


    public static void simulateExpectedTypeFetchDevicesNatively(String[] paths) {

        try{
            createFunction();
        
            String queryStr = "SELECT * FROM get_devices_resource_tree(:string_array_of_paths)";

            Query q = entityManager.createNativeQuery(
                queryStr
            );
            q.setParameter("string_array_of_paths", paths);
            var fetchedResultList = q.getResultList();
            System.out.println(fetchedResultList);

            if (Objects.equals(fetchedResultList, null) || fetchedResultList.size() == 0){
                return;
            }

            @SuppressWarnings("unchecked")
            List<Object> results = q.getResultList();

            List<DeviceEntity> entities = results.stream()
            .map(row -> mapToDeviceEntity((Object[]) row)) // Implement mapping logic
            .collect(Collectors.toList());

            for (DeviceEntity device : entities) {
                System.out.println(device.toString());
            }

        }catch(Exception e){}

        return;
    }


}
