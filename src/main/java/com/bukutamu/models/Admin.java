package com.bukutamu.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "admin")
@Data

public class Admin {
    // public Admin(String email) {
    // this.email = email;
    // }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(nullable = false, length = 45)
    public String name;

    @Column(nullable = false, unique = true, length = 45)

    public String email;

    @Column(nullable = false, length = 256)
    public String password;

}
