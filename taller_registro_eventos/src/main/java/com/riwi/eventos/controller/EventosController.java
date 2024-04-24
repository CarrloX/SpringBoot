package com.riwi.eventos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.eventos.entity.Eventos;
import com.riwi.eventos.service.service_abstract.IEventosService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/eventos")
@AllArgsConstructor
public class EventosController {
    
    @Autowired
    private final IEventosService eventosService;

    @GetMapping
    public ResponseEntity<List<Eventos>>getAll(){
        return ResponseEntity.ok(this.eventosService.getAll());
    }

    // @PostMapping
    // public ResponseEntity<Eventos> insert(@RequestBody Eventos objEvento){
    //     return ResponseEntity.ok(this.eventosService.save(objEvento));
    // }
}
