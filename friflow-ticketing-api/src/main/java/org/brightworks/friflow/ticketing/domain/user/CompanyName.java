package org.brightworks.friflow.ticketing.domain.user;

import org.brightworks.friflow.ticketing.domain.JpaModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author kyel
 */
@Entity
@Table(name = "REF_COMPANY_NAME")
public class CompanyName extends JpaModel {

    /**
     * Company names
     */
    @Column(name = "NAME")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
