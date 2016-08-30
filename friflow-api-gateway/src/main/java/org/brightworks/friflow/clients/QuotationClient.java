package org.brightworks.friflow.clients;

import org.brightworks.friflow.dto.QuotationDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("ticketing-service")
public interface QuotationClient {

    @RequestMapping(method = RequestMethod.GET,value = "/quotations/dummy")
    QuotationDTO getDummy();

    @RequestMapping(method = RequestMethod.GET,value = "/quotations/{ticketNo}")
    QuotationDTO getByTicket(@PathVariable("ticketNo") String ticketNo);

    @RequestMapping(method = RequestMethod.POST,value = "/quotations")
    QuotationDTO save(@RequestBody QuotationDTO quotation);

    @RequestMapping(method = RequestMethod.PUT,value = "/quotations")
    QuotationDTO update(@RequestBody QuotationDTO quotation);
}
