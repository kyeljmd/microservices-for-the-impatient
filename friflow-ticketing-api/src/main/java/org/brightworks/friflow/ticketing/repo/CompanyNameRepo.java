package org.brightworks.friflow.ticketing.repo;

import org.brightworks.friflow.ticketing.domain.user.CompanyName;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author kyel
 */
public interface CompanyNameRepo extends PagingAndSortingRepository<CompanyName,Long>{

    CompanyName findByName(String name);

    List<CompanyName> findByNameContainingIgnoreCase(String name);

}
