package org.brightworks.friflow.listener;

import org.brightworks.friflow.domain.process.production.Production;
import org.brightworks.friflow.domain.process.quotation.Quotation;
import org.joda.time.LocalDateTime;

import javax.persistence.PrePersist;

/**
 * @author kyel
 */
public class TicketNumberGeneratorListener {

    private static final String TICKET_DATE_FORMAT = "mmddyyssa";

    @PrePersist
    public void onPrePersist(Object o) {
        if(o instanceof Quotation){
            ((Quotation) o).setTicketNumber("Q"+
                    LocalDateTime.now()
                            .toString(TICKET_DATE_FORMAT));
            ((Quotation) o).setDateTimeCreated(LocalDateTime.now());
        }

        if(o instanceof Production){
            ((Production) o).setTicketNumber("P"+
                    LocalDateTime.now()
                            .toString(TICKET_DATE_FORMAT));
            ((Production) o).setDateTimeCreated(LocalDateTime.now());
        }
    }

}
