package org.brightworks.friflow.dto;

/**
 * @author kyel
 */
public abstract class BaseProcessDTO {

    private Long id;

    private String createdDateTime;

    private String status;

    private String ticketNumber;

    private long version;

    private boolean editable = true;

    public BaseProcessDTO(Long id, String createdDateTime, String status, String ticketNumber) {
        this.id = id;
        this.createdDateTime = createdDateTime;
        this.status = status;
        this.ticketNumber = ticketNumber;
    }

    public BaseProcessDTO() {
    }


    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }
}
