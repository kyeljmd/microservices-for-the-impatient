package org.brightworks.friflow.service.attachment;

import org.brightworks.friflow.domain.dto.AttachmentDTO;
import org.brightworks.friflow.domain.process.attachment.AttachmentMetaData;
import org.brightworks.friflow.repo.attachment.AttachmentMetaDataRepo;
import org.brightworks.friflow.repo.attachment.AttachmentRepo;
import org.brightworks.friflow.service.attachment.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by KyelDavid on 9/29/2015.
 */
@Transactional
@Service
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    private AttachmentRepo attachmentRepo;

    @Autowired
    private AttachmentMetaDataRepo attachmentMetaDataRepo;

    @Override
    public byte[] getAttachment(Long id) {
        return attachmentRepo.findOne(id).getFile();
    }

    @Override
    public AttachmentDTO getAttachmenetMetaData(Long id) {
        AttachmentDTO dto = new AttachmentDTO();
        AttachmentMetaData metaData = attachmentMetaDataRepo.findOne(id);
        dto.setAttachmentMetaDataId(metaData.getId().toString());
        dto.setAttachmentId(metaData.getAttachment().getId().toString());
        dto.setContentType(metaData.getContentType());
        dto.setFileName(metaData.getFileName());
        dto.setFile(metaData.getAttachment().getFile());
        return dto;
    }

    @Override
    public void deleteAttachment(Long attachmentMetadataId) {
        AttachmentMetaData metaData = attachmentMetaDataRepo.findOne(attachmentMetadataId);
        metaData.setDeleted(true);
        attachmentMetaDataRepo.save(metaData);
    }
}
