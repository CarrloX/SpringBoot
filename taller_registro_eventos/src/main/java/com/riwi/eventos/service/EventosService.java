package com.riwi.eventos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.riwi.eventos.entity.Eventos;
import com.riwi.eventos.repository.EventosRepositorio;
import com.riwi.eventos.service.service_abstract.IEventosService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EventosService implements IEventosService{
    
    private final EventosRepositorio eventosRepositorio;

    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Eventos findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Eventos> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Eventos save(Eventos evento) {
        return this.eventosRepositorio.save(evento);
    }

    @Override
    public List<Eventos> search(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Eventos update(String id, Eventos Eventos) {
        // TODO Auto-generated method stub
        return null;
    }

    

    
}
