package org.brightworks.friflow.controller;

import org.brightworks.friflow.clients.QuotationClient;
import org.brightworks.friflow.dto.QuotationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class QuotationController {

    @Autowired
    private QuotationClient quotationClient;

    @RequestMapping("/quotation-api-dummy")
    public QuotationDTO getDummy(){
         return quotationClient.getDummy();
    }

    @RequestMapping("/quotations/{ticketNo}")
    public QuotationDTO getDummy(@PathVariable("ticketNo") String ticketNo){
        return quotationClient.getByTicket(ticketNo);
    }
}
