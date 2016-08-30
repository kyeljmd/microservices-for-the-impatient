package org.brightworks.friflow.dto;

import java.util.List;

/**
 * @author by kyel on 9/5/2015.
 */
public class QuotationDTO extends BaseProcessDTO{

    private String targetDate;

    private String itemDescription;

    private String customerName;

    private Double price;

    private List<AttachmentDTO> attachments;

    public QuotationDTO() {
    }

    public QuotationDTO(Long id, String createdDateTime, String status, String ticketNumber,
                        String targetDate, String itemDescription, String customerName, Double price) {
        super(id,createdDateTime,status,ticketNumber);
        this.targetDate = targetDate;
        this.itemDescription = itemDescription;
        this.customerName = customerName;
        this.price = price;
    }

    public String getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(String targetDate) {
        this.targetDate = targetDate;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<AttachmentDTO> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<AttachmentDTO> attachments) {
        this.attachments = attachments;
    }
}
