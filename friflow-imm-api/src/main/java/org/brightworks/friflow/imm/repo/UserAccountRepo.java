package org.brightworks.friflow.imm.repo;

import org.brightworks.friflow.imm.domain.UserAccount;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserAccountRepo extends PagingAndSortingRepository<UserAccount,Long>{

    UserAccount findByUsername(String username);

}
