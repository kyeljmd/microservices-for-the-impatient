package org.brightworks.friflow.ticketing.domain.dto;

import java.util.List;

/**
 * @author kyel
 */
public class ProductionDTO {

    private Long id;

    private String ticketNumber;

    private String jobOrderNumber;

    private String purchaseNumber;

    private String processStatus;

    private String priority;

    private String customerName;

    private String description;

    private String dateTimeCreated;

    private String targetDate;

    private Long version;

    private List<AttachmentDTO> attachments;

    private boolean editable = true;

    private String pONumber;

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public String getJobOrderNumber() {
        return jobOrderNumber;
    }

    public void setJobOrderNumber(String jobOrderNumber) {
        this.jobOrderNumber = jobOrderNumber;
    }

    public String getPurchaseNumber() {
        return purchaseNumber;
    }

    public void setPurchaseNumber(String purchaseNumber) {
        this.purchaseNumber = purchaseNumber;
    }

    public String getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateTimeCreated() {
        return dateTimeCreated;
    }

    public void setDateTimeCreated(String dateTimeCreated) {
        this.dateTimeCreated = dateTimeCreated;
    }

    public String getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(String targetDate) {
        this.targetDate = targetDate;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }


    public List<AttachmentDTO> getAttachments() {
        return attachments;
    }

    public String getpONumber() {
        return pONumber;
    }

    public void setpONumber(String pONumber) {
        this.pONumber = pONumber;
    }

    public void setAttachments(List<AttachmentDTO> attachments) {
        this.attachments = attachments;
    }

    @Override
    public String toString() {
        return "ProductionDTO{" +
                "id=" + id +
                ", ticketNumber='" + ticketNumber + '\'' +
                ", jobOrderNumber='" + jobOrderNumber + '\'' +
                ", purchaseNumber='" + purchaseNumber + '\'' +
                ", processStatus='" + processStatus + '\'' +
                ", priority='" + priority + '\'' +
                ", customerName='" + customerName + '\'' +
                ", description='" + description + '\'' +
                ", dateTimeCreated='" + dateTimeCreated + '\'' +
                ", targetDate='" + targetDate + '\'' +
                '}';
    }
}
