package com.riwi.beautySalon.api.dto.controllers;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.beautySalon.api.dto.request.ServiceReq;
import com.riwi.beautySalon.api.dto.response.ServiceResp;
import com.riwi.beautySalon.infrastructure.abstract_services.IServiceService;
import com.riwi.beautySalon.utils.enums.SortType;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/services")
@AllArgsConstructor
public class ServiceController {

    @Autowired
    private final IServiceService service;

    @GetMapping
    public ResponseEntity<Page<ServiceResp>> getAll(
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "5") int size,
        @RequestHeader(required = false) SortType sortType
    ){

        if (Objects.isNull(sortType)){
            sortType = SortType.NONE;
        }

        return ResponseEntity.ok(this.service.getAll(page -1, size, sortType));
    }

    @PostMapping
    public ResponseEntity<ServiceResp> create(
        @Validated @RequestBody ServiceReq request
    ){
        return ResponseEntity.ok(this.service.create(request));        
    }
}