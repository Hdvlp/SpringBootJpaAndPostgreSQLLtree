package com.SpringBootJpaAndLtree.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "devices_resource_tree")
public class DeviceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "ltree")
    private String path;

    public String toString(){
        return String.format("%d_%s_%s", id, name, path);
    }

}

