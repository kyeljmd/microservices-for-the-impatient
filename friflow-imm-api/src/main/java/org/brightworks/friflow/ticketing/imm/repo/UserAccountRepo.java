package org.brightworks.friflow.ticketing.imm.repo;

import org.brightworks.friflow.ticketing.imm.domain.UserAccount;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserAccountRepo extends PagingAndSortingRepository<UserAccount,Long>{

    UserAccount findByUsername(String username);

}
