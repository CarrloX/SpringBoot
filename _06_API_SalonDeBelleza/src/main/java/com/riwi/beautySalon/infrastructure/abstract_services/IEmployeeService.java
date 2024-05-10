package com.riwi.beautySalon.infrastructure.abstract_services;

import com.riwi.beautySalon.api.dto.request.EmployeeReq;
import com.riwi.beautySalon.api.dto.response.EmployeeResp;

public interface IEmployeeService
        extends CrudService<EmployeeReq, EmployeeResp, Long> {
    public final String FIELD_BY_SORT = "firstName";

}
