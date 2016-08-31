package org.brightworks.friflow.ticketing.service;

import org.brightworks.friflow.ticketing.domain.process.production.Production;
import org.brightworks.friflow.ticketing.domain.process.quotation.Quotation;
import org.brightworks.friflow.ticketing.repo.production.ProductionRepo;
import org.brightworks.friflow.ticketing.repo.quotation.QuotationRepo;
import org.brightworks.friflow.ticketing.domain.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by kyel on 6/9/2016.
 */
@Service
public class BaseProcessTicketService {

    @Autowired
    private QuotationRepo quotationRepo;

    @Autowired
    private ProductionRepo productionRepo;

    public void deleteTicket(Long ticketId, Module module) {
        switch (module) {
            case PRODUCTION:
                Production production = productionRepo.findOne(ticketId);
                production.setDeleted(true);
                productionRepo.save(production);
                break;
            case QUOTATION:
                Quotation quotation = quotationRepo.findOne(ticketId);
                quotation.setDeleted(true);
                quotationRepo.save(quotation);
                break;
        }
    }
}
