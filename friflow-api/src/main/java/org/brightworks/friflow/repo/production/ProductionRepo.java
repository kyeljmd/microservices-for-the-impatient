package org.brightworks.friflow.repo.production;

import org.brightworks.friflow.domain.process.production.Production;
import org.brightworks.friflow.repo.ParentProcessRepo;
import org.joda.time.LocalDate;

import java.util.List;

/**
 * @author kyel
 */
public interface ProductionRepo extends ParentProcessRepo<Production,Long>,ProductionRepoCustom {

    Production findByTicketNumber(String ticketNumber);

    Production findByJobOrderNumber(String jobOrderNumber);

    Production findByQuotationTicketNumber(String quotationTicketNumber);

    List<Production> findTop5ByTargetDateLessThanEqual(LocalDate targetDate);
}
