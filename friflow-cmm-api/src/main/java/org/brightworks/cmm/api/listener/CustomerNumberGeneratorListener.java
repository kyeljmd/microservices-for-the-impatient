package org.brightworks.cmm.api.listener;

import org.brightworks.cmm.api.domain.Customer;

import javax.persistence.PrePersist;
import java.util.UUID;

/**
 * Created by kyel on 9/12/2016.
 */
public class CustomerNumberGeneratorListener {
    @PrePersist
    public void onPrePersist(Object o) {
        if(o instanceof Customer){
            UUID uuid  = UUID.randomUUID();
            ((Customer)o).setCustomerNumber( uuid.toString().replace("-", ""));
        }
    }
}
