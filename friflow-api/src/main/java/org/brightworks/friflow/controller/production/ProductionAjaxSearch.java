package org.brightworks.friflow.controller.production;

import org.brightworks.friflow.domain.dto.ProductionDTO;
import org.brightworks.friflow.domain.process.ProcessStatus;
import org.brightworks.friflow.service.production.ProductionService;
import org.brightworks.friflow.util.DataTablesResponse;
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
public class ProductionAjaxSearch {

    @Autowired
    private ProductionService productionService;

    @RequestMapping("/production/ajax/search")
    public DataTablesResponse<ProductionDTO> searchQuotations(@RequestParam(required=false) int sEcho,
                                                              @RequestParam (required=false) int iDisplayStart,
                                                              @RequestParam (required=false) int iDisplayLength,
                                                              @RequestParam (required=false) int iColumns,
                                                              @RequestParam (required=false) int iSortCol_0,
                                                              @RequestParam (required=false)String sSortDir_0,
                                                              @RequestParam (required=false) String ticketNumber,
                                                              @RequestParam (required=false) String jobOrderNumber,
                                                              @RequestParam (required=false) String purchaseNumber,
                                                              @RequestParam (required=false) String clientName,
                                                              @RequestParam (required=false) String description,
                                                              @RequestParam (required=false) String startDate,
                                                              @RequestParam (required=false) String endDate,
                                                              @RequestParam(required=false) ProcessStatus status){

        int page = (int) Math.ceil(iDisplayStart/iDisplayLength);
        Page<ProductionDTO> quotationDTOs = productionService
                .search(jobOrderNumber,purchaseNumber,description,clientName,
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
