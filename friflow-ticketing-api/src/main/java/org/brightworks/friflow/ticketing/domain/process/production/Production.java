package org.brightworks.friflow.ticketing.domain.process.production;

import org.brightworks.friflow.ticketing.domain.process.BaseProcess;
import org.brightworks.friflow.ticketing.domain.process.attachment.AttachmentMetaData;
import org.brightworks.friflow.ticketing.domain.process.quotation.Quotation;

import javax.persistence.*;
import java.util.List;

/**
 * Created by kyeljohndavid on 10/8/2015.
 */
@Entity
@Table(name = "TXN_PRODUCTION")
public class Production extends BaseProcess {

    @OneToOne
    private Quotation quotation;

    /**
     * If no quotation, This will have value
     */
    @Column(name = "PURCHASE_NUMBER")
    private String purchaseNumber;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PRIORITY")
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Column(name = "JOB_ORDER_NUMBER")
    private String jobOrderNumber;

    @Column(name = "PONUmber")
    private String pONumber;

    @OneToMany
    private List<AttachmentMetaData> attachments;

    public List<AttachmentMetaData> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<AttachmentMetaData> attachments) {
        this.attachments = attachments;
    }

    public Quotation getQuotation() {
        return quotation;
    }

    public void setQuotation(Quotation quotation) {
        this.quotation = quotation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getJobOrderNumber() {
        return jobOrderNumber;
    }

    public void setJobOrderNumber(String jobOrderNumber) {
        this.jobOrderNumber = jobOrderNumber;
    }

    public String getpONumber() {
        return pONumber;
    }

    public void setpONumber(String pONumber) {
        this.pONumber = pONumber;
    }

    @Override
    public String toString() {
        return "Production{" +
                "quotation=" + quotation +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                ", jobOrderNumber='" + jobOrderNumber + '\'' +
                "} " + super.toString();
    }

    public String getPurchaseNumber() {
        return purchaseNumber;
    }

    public void setPurchaseNumber(String purchaseNumber) {
        this.purchaseNumber = purchaseNumber;
    }
}
