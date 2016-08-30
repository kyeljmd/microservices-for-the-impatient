package org.brightworks.friflow.controller.quotation;

import org.brightworks.friflow.domain.dto.QuotationDTO;
import org.brightworks.friflow.exceptions.FormDataIntegrityException;
import org.brightworks.friflow.service.quotation.QuotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

/**
 * @author kdavid
 */
@RestController
@RequestMapping("/quotations")
public class QuotationResource {

    @Autowired
    private QuotationService quotationService;

    @RequestMapping(value = "/{ticketNo}", method = GET)
    public ResponseEntity<QuotationDTO> get(@PathVariable String ticketNo) {
        return new ResponseEntity<>(quotationService.findOne(ticketNo), OK);
    }

    @RequestMapping(method = POST)
    public ResponseEntity<QuotationDTO> add(@RequestBody QuotationDTO quotation) throws FormDataIntegrityException {
        return new ResponseEntity<>(quotationService.save(quotation,null), OK);
    }

    @RequestMapping(method = PUT)
    public ResponseEntity<QuotationDTO> update(@RequestBody QuotationDTO quotation) throws FormDataIntegrityException {
        return new ResponseEntity<>(quotationService.save(quotation,null), OK);
    }

    /**
     * Added Dummy
     * @return
     */
    @RequestMapping(value = "/dummy",method = GET)
    public ResponseEntity<QuotationDTO> getDummy(){
        return new ResponseEntity(new QuotationDTO(),OK);
    }
}
