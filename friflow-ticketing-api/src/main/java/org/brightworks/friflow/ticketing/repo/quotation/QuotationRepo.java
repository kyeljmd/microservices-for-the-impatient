package org.brightworks.friflow.ticketing.repo.quotation;

import org.brightworks.friflow.ticketing.domain.process.quotation.Quotation;
import org.brightworks.friflow.ticketing.repo.ParentProcessRepo;

import java.util.List;

/**
 * @author kyel
 */
public interface QuotationRepo  extends ParentProcessRepo<Quotation,Long>,QuotationRepoCustom {

    Quotation findByTicketNumber(String ticketNumber);

    List<Quotation> findByTicketNumberContainsIgnoreCase(String ticketNumber);

}
