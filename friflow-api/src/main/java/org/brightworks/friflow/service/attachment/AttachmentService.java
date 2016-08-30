package org.brightworks.friflow.service.attachment;

import org.brightworks.friflow.domain.dto.AttachmentDTO;

/**
 * Created by KyelDavid on 9/29/2015.
 */
public interface AttachmentService {

    byte[] getAttachment(Long id);

    AttachmentDTO getAttachmenetMetaData(Long id);

    void deleteAttachment(Long attachmentMetadataId);

}
