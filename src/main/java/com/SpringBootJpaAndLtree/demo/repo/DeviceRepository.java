package com.SpringBootJpaAndLtree.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.SpringBootJpaAndLtree.demo.model.DeviceEntity;

import java.util.List;

public interface DeviceRepository extends JpaRepository<DeviceEntity, Integer> {

    @Query(value = "SELECT * FROM devices_resource_tree t WHERE t.path <@ ANY(CAST(?1 AS ltree[]))", nativeQuery = true)
    List<DeviceEntity> findByPathContaining(@Param("paths") String[] paths);

    @Query(value = "SELECT CAST(pg_typeof(?1) AS text)", nativeQuery = true)
    String findPgTypeofBy(@Param("valOfADataType") Integer valOfADataType); 

    @Query(value = "SELECT CAST(pg_typeof(?1) AS text)", nativeQuery = true)
    String findPgTypeofBy(@Param("valOfADataType") Integer[] valOfADataType); 

    @Query(value = "SELECT CAST(pg_typeof(?1) AS text)", nativeQuery = true)
    String findPgTypeofBy(@Param("valOfADataType") Double valOfADataType); 

    @Query(value = "SELECT CAST(pg_typeof(?1) AS text)", nativeQuery = true)
    String findPgTypeofBy(@Param("valOfADataType") String valOfADataType); 

    @Query(value = "SELECT CAST(pg_typeof(?1) AS text)", nativeQuery = true)
    String findPgTypeofBy(@Param("valOfADataType") List<String> valOfADataType);   

    @Query(value = "SELECT CAST(pg_typeof(?1) AS text)", nativeQuery = true)
    String findPgTypeofBy(@Param("valOfADataType") String[] valOfADataType);   

}
