package org.brightworks.friflow.domain.process.attachment;

import org.brightworks.friflow.domain.JpaModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author kdavid on 8/22/2015.
 */
@Entity
@Table(name = "TXN_ATTACHMENT")
public class Attachment extends JpaModel{

    @Column(name="FILE")
    private byte[] file;

    @OneToOne
    private AttachmentMetaData attachmentMetaData;

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public AttachmentMetaData getAttachmentMetaData() {
        return attachmentMetaData;
    }

    public void setAttachmentMetaData(AttachmentMetaData attachmentMetaData) {
        this.attachmentMetaData = attachmentMetaData;
    }
}
