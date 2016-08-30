package org.brightworks.friflow.admin.repo;

import org.brightworks.friflow.admin.domain.UserAccount;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserAccountRepo extends PagingAndSortingRepository<UserAccount,Long>{

    UserAccount findByUsername(String username);

}
