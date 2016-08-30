package org.brightworks.friflow.repo.attachment;

import org.brightworks.friflow.domain.process.attachment.AttachmentMetaData;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by KyelDavid on 9/29/2015.
 */
public interface AttachmentMetaDataRepo extends PagingAndSortingRepository<AttachmentMetaData,Long> {
}
