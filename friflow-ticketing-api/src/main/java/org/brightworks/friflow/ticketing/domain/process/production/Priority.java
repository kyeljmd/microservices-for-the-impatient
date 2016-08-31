package org.brightworks.friflow.ticketing.domain.process.production;

/**
 * @author kyel
 */
public enum  Priority {
    RUSH(2),
    MAJOR(5),
    MINOR(10);

    public int days;

    Priority(int days) {
        this.days = days;
    }
}
