package org.brightworks.friflow.ticketing.repo.attachment;

import org.brightworks.friflow.ticketing.domain.process.attachment.Attachment;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by KyelDavid on 9/29/2015.
 */
public interface AttachmentRepo extends PagingAndSortingRepository<Attachment,Long>{
}
