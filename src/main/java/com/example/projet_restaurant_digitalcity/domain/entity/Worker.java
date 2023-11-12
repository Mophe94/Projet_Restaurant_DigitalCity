package com.example.projet_restaurant_digitalcity.domain.entity;

import com.example.projet_restaurant_digitalcity.domain.WorkerRoles;
import jakarta.persistence.*;
import org.aspectj.weaver.ast.Or;

import java.util.List;

@Entity
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="Worker_Id",nullable = false)
    private long id;
    @Column(name = "Worker_Name",nullable = false)
    private String name;
    @Column(name = "Worker_Username",nullable = false)
    private String username;
    @Column(name = "Worker_password",nullable = false)
    private String password;
    @Column(name = "Worker_Role",nullable = false)
    @Enumerated(EnumType.STRING)
    private WorkerRoles roles;
//    @OneToMany(mappedBy ="worker")
//    private List<Order>orders;

}
