package org.brightworks.friflow.service.quotation;

import org.brightworks.friflow.domain.dto.QuotationDTO;
import org.brightworks.friflow.domain.process.ProcessStatus;
import org.brightworks.friflow.exceptions.FormDataIntegrityException;
import org.joda.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author kyel
 */
public interface QuotationService {

    QuotationDTO save(QuotationDTO quotationDTO,List<MultipartFile> attachments) throws FormDataIntegrityException;

    QuotationDTO findOne(Long id);

    QuotationDTO findOne(String ticketNumber);

    Page<QuotationDTO> search(String ticketNumber,
                              String description,
                              String clientName,
                              LocalDate startDate,
                              LocalDate endDate,
                              ProcessStatus status,
                              Pageable pageable);
}
