package org.brightworks.friflow.service.reference;

import org.brightworks.friflow.domain.dto.CompanyNameDTO;

import java.util.List;

/**
 * @author kyel
 */
public interface ReferenceService {

    List<CompanyNameDTO> searchCompanyName(String term);
}
