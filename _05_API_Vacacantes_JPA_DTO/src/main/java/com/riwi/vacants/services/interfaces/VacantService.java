package com.riwi.vacants.services.interfaces;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.vacants.entity.Vacant;
import com.riwi.vacants.repos.CompanyRepository;
import com.riwi.vacants.repos.VacantRepository;
import com.riwi.vacants.utils.dto.request.VacantRequest;
import com.riwi.vacants.utils.dto.response.CompanyToVacantResponse;
import com.riwi.vacants.utils.dto.response.VacantsResponse;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VacantService implements IVacantsService{

    @Autowired
    private final VacantRepository vacantRepository;
    @Autowired
    private final CompanyRepository companyRepository;

    @Override
    public Page<VacantsResponse> getAll(int page, int size) {

        if (page <0) page = 0;

        PageRequest pagination = PageRequest.of(page, size);

        //obetenemos todas las vacantes, las iteramos para convertir cada una

        return this.vacantRepository.findAll(pagination)
            .map(vacant -> this.entityToRespons(vacant)); 
    }

    @Override
    public VacantsResponse create(VacantRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public VacantsResponse update(VacantRequest request, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public VacantsResponse getById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    private VacantsResponse entityToRespons(Vacant entity){
        //creamos la instancia del DTO del vacante
        VacantsResponse response = new VacantsResponse();

        //copiar  toda la enitidad en el DTO
        BeanUtils.copyProperties(entity, response);

        //creamos la instancia del dto de compañia dentro de la vacante
        CompanyToVacantResponse companyDto = new CompanyToVacantResponse();

        //copio todas las propiedades de la compañia que se encuentra dentro de la entidad
        //(vacante) en el dto de repuesta
    
        BeanUtils.copyProperties(entity.getCompany(), companyDto);

        //agregamos el dto lleno a la respuesta final
        response.setCompany(companyDto);

        return response;
    }
    
}
