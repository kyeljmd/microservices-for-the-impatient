package org.brightworks.friflow.ticketing.service.reference;

import org.brightworks.friflow.ticketing.domain.dto.CompanyNameDTO;

import java.util.List;

/**
 * @author kyel
 */
public interface ReferenceService {

    List<CompanyNameDTO> searchCompanyName(String term);
}
