package com.bukutamu.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "agenda")
@Data
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    public int id;

    @Column(nullable = false, length = 255)
    public String name;

    // @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(nullable = false)

    public String date;

    // @Override
    // public String toString() {
    // return "\"" + id + ":{id : " + id + ", name : " + name + ", date : " + date +
    // "}";
    // }

}
