package com.riwi.eventos.service.service_abstract;

import java.util.List;

import com.riwi.eventos.entity.Eventos;

public interface IEventosService {
    
    public Eventos save(Eventos evento);

    public List<Eventos> getAll();
    
    public Eventos findById(String id);

    public void delete(String id);

    public Eventos update(String id,Eventos Eventos);

    public List<Eventos> search(String name);
}
