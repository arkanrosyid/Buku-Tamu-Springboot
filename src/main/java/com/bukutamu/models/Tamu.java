package com.bukutamu.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tamu")
@Data
public class Tamu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    public int id;

    @Column(nullable = false, length = 255)
    public int idAgenda;

    @Column(nullable = false, length = 255)
    public String name;

    @Column(nullable = false, length = 255)
    public String email;

    @Column(nullable = true, length = 500)
    public String desctription;

    @Column(nullable = true, length = 500)
    public String agency;

    // @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Column(nullable = false)

    public String date;
}
