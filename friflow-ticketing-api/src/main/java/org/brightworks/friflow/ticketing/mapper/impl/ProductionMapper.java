package org.brightworks.friflow.ticketing.mapper.impl;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.brightworks.friflow.ticketing.domain.user.CompanyName;
import org.brightworks.friflow.ticketing.domain.dto.ProductionDTO;
import org.brightworks.friflow.ticketing.domain.process.ProcessStatus;
import org.brightworks.friflow.ticketing.domain.process.production.Priority;
import org.brightworks.friflow.ticketing.domain.process.production.Production;
import org.brightworks.friflow.ticketing.repo.CompanyNameRepo;
import org.brightworks.friflow.ticketing.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author kdavid
 */
@Component
public class ProductionMapper extends CustomMapper<Production, ProductionDTO> {

    @Autowired
    private CompanyNameRepo companyNameRepo;

    public void mapAtoB(Production production, ProductionDTO dto, MappingContext context) {
        dto.setCustomerName(production.getClientName().getName());
        dto.setTargetDate(DateUtil.formatDate(production.getTargetDate()));
        dto.setDescription(production.getDescription());
        dto.setDateTimeCreated(DateUtil.formatDateTime(production.getDateTimeCreated(), "MM-dd-yyyy hh:mm aa"));
        dto.setTargetDate(DateUtil.formatDate(production.getTargetDate()));
        dto.setTicketNumber(production.getTicketNumber());
        if(production.getQuotation() != null){
            dto.setPurchaseNumber(production.getQuotation().getTicketNumber());
        }else {
            dto.setPurchaseNumber(production.getPurchaseNumber());
        }
        if(production.getProcessStatus() != null
                && (production.getProcessStatus()
                .equals(ProcessStatus.COMPLETED))) {
            dto.setEditable(false);
        }

    }

    public void mapBtoA(ProductionDTO dto, Production production, MappingContext context) {
        if(doesCompanyNameExist(dto.getCustomerName())){
            production.setClientName(companyNameRepo.findByName(dto.getCustomerName().toUpperCase()));
        }else{
            CompanyName companyName = new CompanyName();
            companyName.setName(dto.getCustomerName().toUpperCase());
            production.setClientName(companyNameRepo.save(companyName));
        }

        Priority priority = Priority.valueOf(dto.getPriority());
        switch (priority) {
            case RUSH:
                production.setTargetDate(DateUtil.toLocalDate(dto.getTargetDate()).plusDays(Priority.RUSH.days));
                break;
            case MAJOR:
                production.setTargetDate(DateUtil.toLocalDate(dto.getTargetDate()).plusDays(Priority.MAJOR.days));
                break;
            case MINOR:
                production.setTargetDate(DateUtil.toLocalDate(dto.getTargetDate()).plusDays(Priority.MINOR.days));
                break;
            default:
                throw new IllegalArgumentException("Given Priority does not match");
        }
    }

    private boolean doesCompanyNameExist(String companyName){
        return (companyNameRepo.findByName(companyName.toUpperCase()) != null);
    }

}
