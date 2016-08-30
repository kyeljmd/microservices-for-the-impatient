package org.brightworks.friflow.controller;

import org.brightworks.friflow.service.BaseProcessTicketService;
import org.brightworks.friflow.domain.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TicketRemovalController {

    @Autowired
    private BaseProcessTicketService baseProcessTicketService;

    @RequestMapping("/delete/{module}/{id}")
    public ResponseEntity deleteTicket(@PathVariable("id")Long id,
                                       @PathVariable("module")Module module) {
        baseProcessTicketService.deleteTicket(id,module);
        return new ResponseEntity(HttpStatus.OK);
    }
}
