package org.brightworks.friflow.repo;

import org.brightworks.friflow.domain.process.BaseProcess;
import org.brightworks.friflow.domain.process.ProcessStatus;
import org.joda.time.LocalDate;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kyel on 12/25/2015.
 */
@NoRepositoryBean
public interface ParentProcessRepo<T extends BaseProcess,ID extends Serializable> extends PagingAndSortingRepository<T,ID> {
    List<T> findTop5ByTargetDateLessThanEqualAndProcessStatus(LocalDate targetDate,ProcessStatus processStatus);
}
