package com.bukutamu.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bukutamu.models.Tamu;

public interface TamuRepository extends JpaRepository<Tamu, Integer> {
    // @Query("SELECT u FROM Tamu u WHERE u.idAgenda = ?1")
    // List<Tamu> getAllUseIdAgenda(List<Integer> idAgenda);
    List<Tamu> findAllByIdAgenda(Integer idAgenda);

    @Query("SELECT u FROM Tamu u WHERE u.id = ?1")
    Tamu findByID(Integer id);
}
