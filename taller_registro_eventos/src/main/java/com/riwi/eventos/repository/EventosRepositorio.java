package com.riwi.eventos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.eventos.entity.Eventos;

@Repository
public interface EventosRepositorio extends JpaRepository<Eventos, String>{
    public Eventos findByName (String name);
}
