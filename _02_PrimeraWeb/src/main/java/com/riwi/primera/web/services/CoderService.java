package com.riwi.primera.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.riwi.primera.web.entity.Coder;
import com.riwi.primera.web.repository.CoderRepository;

@Service//esta clase sera un servicio
public class CoderService {

    //autowired le indica a springboot que esto es una inyeccion de dependencias

    @Autowired
    private CoderRepository objCoderRepository;

    //servicio para listar todos los coders

    public List<Coder> findAll(){
        return this.objCoderRepository.findAll();
    }
}
