package org.brightworks.friflow.service;

import org.brightworks.friflow.domain.dto.ProductionDTO;
import org.brightworks.friflow.domain.dto.QuotationDTO;
import org.brightworks.friflow.domain.process.production.Production;
import org.brightworks.friflow.domain.process.quotation.Quotation;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Service;

/**
 * Created by kyel on 5/16/2016.
 */
@Service
public class TicketGeneratorService {

    private static final String TICKET_DATE_FORMAT = "mmddyyssa";

    public String ticketNumber(Object baseProcess) {
        if(baseProcess instanceof Quotation || baseProcess instanceof QuotationDTO){
            return "Q"+
                    LocalDateTime.now()
                            .toString(TICKET_DATE_FORMAT);
        }

        if(baseProcess instanceof Production || baseProcess instanceof ProductionDTO) {
            return "P"+
                    LocalDateTime.now()
                            .toString(TICKET_DATE_FORMAT);
        }
        return "";
    }
}
