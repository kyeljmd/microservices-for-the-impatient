package org.brightworks.friflow.repo.production;

import org.brightworks.friflow.domain.dto.ProductionDTO;
import org.brightworks.friflow.domain.process.ProcessStatus;
import org.joda.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author kyel
 */
public interface ProductionRepoCustom {

    Page<ProductionDTO> search(String jobOrderNumber,
                               String purchaseNumber,
                               String description,
                               String clientName,
                               LocalDate startDate,
                               LocalDate endDate,
                               ProcessStatus status,
                               Pageable pageable);

}
