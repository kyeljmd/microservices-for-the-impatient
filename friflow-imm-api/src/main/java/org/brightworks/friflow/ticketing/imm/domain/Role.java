package org.brightworks.friflow.ticketing.imm.domain;


public enum Role {

    ROLE_ADMIN("Admin"),
    ROLE_QUOTATION_ADD("Quotation - ADD"),
    ROLE_QUOTATION_VIEW("Quotation - VIEW"),
    ROLE_QUOTATION_EDIT("Quotation - EDIT"),
    ROLE_PRODUCTION_ADD("Production - ADD"),
    ROLE_PRODUCTION_EDIT("Production - EDIT"),
    ROLE_PRODUCTION_VIEW("Production - VIEW");

    Role(String code){
        this.code = code;
    }

    private String code;

    public String getCode() {
        return code;
    }
}
