package org.brightworks.friflow.controller.production;

import org.brightworks.friflow.domain.dto.ProductionDTO;
import org.brightworks.friflow.exceptions.FormDataIntegrityException;
import org.brightworks.friflow.service.production.ProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by kyel on 8/9/2016.
 */
@RestController
@RequestMapping("/productions")
public class ProductionResource {

    @Autowired
    private ProductionService productionService;

    @RequestMapping(value = "/{ticketNo}", method = GET)
    public ResponseEntity<ProductionDTO> get(@PathVariable String ticketNo) {
        return new ResponseEntity<>(productionService.findOne(ticketNo), OK);
    }

    @RequestMapping(method = POST)
    public ResponseEntity<ProductionDTO> add(@RequestBody ProductionDTO production) throws FormDataIntegrityException {
        return new ResponseEntity<>(productionService.save(production,null), OK);
    }

    @RequestMapping(method = PUT)
    public ResponseEntity<ProductionDTO> update(@RequestBody ProductionDTO production) throws FormDataIntegrityException {
        return new ResponseEntity<>(productionService.save(production,null), OK);
    }

    /**
     * Added Dummy
     * @return
     */
    @RequestMapping(value = "/dummy",method = GET)
    public ResponseEntity<ProductionDTO> getDummy(){
        return new ResponseEntity(new ProductionDTO(),OK);
    }
}
