package org.brightworks.friflow.ticketing.imm.domain;

import javax.persistence.*;

/**
 * @author kyel
 */
@MappedSuperclass
public class JpaModel {

    public static final String GENERATOR_NAME = "DEFAULT_ID_GEN";

    public static final String TABLE_NAME = "SEQ_ENTITY_ID";

    public static final int ID_INITIAL_VALUE = 1000;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = GENERATOR_NAME)
    @TableGenerator(name = GENERATOR_NAME, table = TABLE_NAME, initialValue = ID_INITIAL_VALUE)
    private Long id;

    @Version
    private long version;

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
}
