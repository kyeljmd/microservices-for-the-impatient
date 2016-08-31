package org.brightworks.friflow.ticketing.service.production;

import org.brightworks.friflow.ticketing.domain.dto.ProductionDTO;
import org.brightworks.friflow.ticketing.domain.process.ProcessStatus;
import org.brightworks.friflow.ticketing.exceptions.FormDataIntegrityException;
import org.joda.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author  by kyel on 10/9/2015.
 */
public interface ProductionService {

    ProductionDTO save(ProductionDTO quotationDTO,List<MultipartFile> attachments) throws FormDataIntegrityException;

    ProductionDTO findOne(Long id);

    ProductionDTO findOne(String ticketNumber);

    ProductionDTO findOneByJobOrderNumber(String jobOrderNumber);

    Page<ProductionDTO> search(String jobOrderNumber,
                               String purchaseNumber,
                               String description,
                               String clientName,
                               LocalDate startDate,
                               LocalDate endDate,
                               ProcessStatus status,
                               Pageable pageable);

}
