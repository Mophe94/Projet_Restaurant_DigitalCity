package com.example.projet_restaurant_digitalcity.domain.entity;

import com.example.projet_restaurant_digitalcity.domain.WorkerRoles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
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
    private WorkerRoles role;
    @OneToMany(mappedBy ="worker")
    private List<OrderProduct>orders;

}
