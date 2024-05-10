package com.riwi.beautySalon.infrastructure.abstract_services;

import com.riwi.beautySalon.api.dto.request.AppointmentReq;
import com.riwi.beautySalon.api.dto.response.AppointmentResp;

public interface IAppointmentService 
    extends  CrudService<AppointmentReq,AppointmentResp, Long> {
    public final String FIELD_BY_SORT = "dateTime";
}
