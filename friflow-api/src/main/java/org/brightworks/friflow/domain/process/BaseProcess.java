package org.brightworks.friflow.domain.process;

import org.brightworks.friflow.domain.user.CompanyName;
import org.brightworks.friflow.domain.JpaModel;
import org.brightworks.friflow.listener.TicketNumberGeneratorListener;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import javax.persistence.*;

/**
 * @author kdavid
 */
@MappedSuperclass
@EntityListeners(TicketNumberGeneratorListener.class)
public abstract class BaseProcess  extends JpaModel {

    @Column(name="date_time_created", nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime dateTimeCreated;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private ProcessStatus processStatus;

    @Column(name = "target_date")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    protected LocalDate targetDate;

    @Column(name = "ticket_number")
    private String ticketNumber;

    @OneToOne
    private CompanyName clientName;

    @Column(name = "deleted",nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean deleted = false;

    public LocalDateTime getDateTimeCreated() {
        return dateTimeCreated;
    }

    public void setDateTimeCreated(LocalDateTime dateTimeCreated) {
        this.dateTimeCreated = dateTimeCreated;
    }

    public ProcessStatus getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(ProcessStatus processStatus) {
        this.processStatus = processStatus;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public CompanyName getClientName() {
        return clientName;
    }

    public void setClientName(CompanyName clientName) {
        this.clientName = clientName;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }


    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "BaseProcess{" +
                "processStatus=" + processStatus +
                ", ticketNumber='" + ticketNumber + '\'' +
                ", clientName=" + clientName +
                "} " + super.toString();
    }
}
