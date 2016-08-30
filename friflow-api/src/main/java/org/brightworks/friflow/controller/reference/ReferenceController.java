package org.brightworks.friflow.controller.reference;

import org.brightworks.friflow.domain.dto.CompanyNameDTO;
import org.brightworks.friflow.service.reference.ReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author kyel
 */
@RestController
public class ReferenceController {

    @Autowired
    private ReferenceService referenceService;

    @RequestMapping(value = "company/search")
    public List<CompanyNameDTO> searchCompany(@RequestParam("term")String term){
        return referenceService.searchCompanyName(term);
    }
}
