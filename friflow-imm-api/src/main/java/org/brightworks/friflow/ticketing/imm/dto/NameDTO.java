package org.brightworks.friflow.ticketing.imm.dto;

/**
 * Created by kyel on 1/3/2016.
 */
public class NameDTO {

    private String givenName;

    private String familyName;

    private String middleName;

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
}
