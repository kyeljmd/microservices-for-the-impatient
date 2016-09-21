package org.brightworks.friflow.ticketing.controller.quotation;

import org.brightworks.friflow.ticketing.domain.dto.QuotationDTO;
import org.brightworks.friflow.ticketing.domain.process.ProcessStatus;
import org.brightworks.friflow.ticketing.service.quotation.QuotationService;
import org.brightworks.friflow.ticketing.util.DataTablesResponse;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kyel
 */
@RestController
public class QuotationAjaxSearch {

    @Autowired
    private QuotationService quotationService;

    @RequestMapping("/quotation/ajax/search")
    public DataTablesResponse<QuotationDTO> searchQuotations(@RequestParam(required=false) int sEcho,
                                                             @RequestParam (required=false) int iDisplayStart,
                                                             @RequestParam (required=false) int iDisplayLength,
                                                             @RequestParam (required=false) int iColumns,
                                                             @RequestParam (required=false) int iSortCol_0,
                                                             @RequestParam (required=false)String sSortDir_0,
                                                             @RequestParam (required=false) String ticketNumber,
                                                             @RequestParam (required=false) Long clientId,
                                                             @RequestParam (required=false) String description,
                                                             @RequestParam (required=false) String startDate,
                                                             @RequestParam (required=false) String endDate,
                                                             @RequestParam(required=false)ProcessStatus status){

        int page = (int) Math.ceil(iDisplayStart/iDisplayLength);
        Page<QuotationDTO> quotationDTOs = quotationService
                .search(ticketNumber,
                        description,
                        clientId,
                        !startDate.isEmpty() ?  LocalDate.parse(startDate, DateTimeFormat.forPattern("MM-dd-yyyy")) : null,
                        !endDate.isEmpty() ?  LocalDate.parse(endDate, DateTimeFormat.forPattern("MM-dd-yyyy")) : null,
                        status,
                        iDisplayLength != -1 ? new PageRequest(page, iDisplayLength) : null);

        return new DataTablesResponse<>(quotationDTOs.getContent(),
                sEcho,
                quotationDTOs.getTotalElements(),
                quotationDTOs.getTotalElements());
    }
}
