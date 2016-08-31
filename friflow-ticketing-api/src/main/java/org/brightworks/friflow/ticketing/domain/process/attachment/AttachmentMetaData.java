package org.brightworks.friflow.ticketing.domain.process.attachment;

import org.brightworks.friflow.ticketing.domain.JpaModel;

import javax.persistence.*;

/**
 * @author kdavid
 */
@Entity
@Table(name = "TXN_ATTACHMENT_META_DATA")
public class AttachmentMetaData extends JpaModel{

    @Column(name = "FILE_NAME")
    private String fileName;

    @Column(name = "CONTENT_TYPE")
    private String contentType;

    @OneToOne(mappedBy = "attachmentMetaData", cascade = CascadeType.ALL)
    private Attachment attachment;

    @Column(name = "deleted")
    private boolean deleted;

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Attachment getAttachment() {
        return attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "AttachmentMetaData{" +
                "fileName='" + fileName + '\'' +
                ", contentType='" + contentType + '\'' +
                ", attachment=" + attachment +
                "} " + super.toString();
    }
}
