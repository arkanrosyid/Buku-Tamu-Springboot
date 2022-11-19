package com.bukutamu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bukutamu.models.Agenda;

public interface AgendaRepository extends JpaRepository<Agenda, Integer> {
    @Query("SELECT u FROM Agenda u WHERE u.id = ?1")
    Agenda findByID(Integer id);

}
