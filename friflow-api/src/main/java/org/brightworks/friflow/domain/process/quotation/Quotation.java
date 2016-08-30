package org.brightworks.friflow.domain.process.quotation;

import org.brightworks.friflow.domain.process.BaseProcess;
import org.brightworks.friflow.domain.process.attachment.AttachmentMetaData;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author kdavid on 8/22/2015.
 */
@Entity
@Table(name = "TXN_QUOTATION")
public class Quotation extends BaseProcess {

    @Deprecated
    @Column(name = "CUSTOMER_NAME")
    private String customerName;

    @Column(name = "DESCRIPTION")
    private String itemDescription;

    @Column(name = "PRICE")
    private BigDecimal price;

    @OneToMany
    private List<AttachmentMetaData> attachments;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<AttachmentMetaData> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<AttachmentMetaData> attachments) {
        this.attachments = attachments;
    }


    @Override
    public String toString() {
        return "Quotation{" +
                "itemDescription='" + itemDescription + '\'' +
                ", targetDate=" + targetDate +
                ", price=" + price +
                ", attachments=" + attachments +
                "} " + super.toString();
    }
}
