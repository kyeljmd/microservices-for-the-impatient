package org.brightworks.friflow.ticketing.repo.attachment;

import org.brightworks.friflow.ticketing.domain.process.attachment.AttachmentMetaData;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by KyelDavid on 9/29/2015.
 */
public interface AttachmentMetaDataRepo extends PagingAndSortingRepository<AttachmentMetaData,Long> {
}
