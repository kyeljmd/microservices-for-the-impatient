package org.brightworks.friflow.ticketing.dto;

/**
 * Created by KyelDavid on 9/29/2015.
 */
public class AttachmentDTO {

    private String fileName;

    private String contentType;

    private String attachmentMetaDataId;

    private String attachmentId;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getAttachmentMetaDataId() {
        return attachmentMetaDataId;
    }

    public void setAttachmentMetaDataId(String attachmentMetaDataId) {
        this.attachmentMetaDataId = attachmentMetaDataId;
    }
}
