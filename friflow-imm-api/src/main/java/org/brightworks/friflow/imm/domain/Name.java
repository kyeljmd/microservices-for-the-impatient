package org.brightworks.friflow.imm.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * author by kyel
 */
@Embeddable
public class Name {

    @Column(name = "given_name")
    private String givenName;

    @Column(name = "family_name")
    private String familyName;

    @Column(name = "middle_name")
    private String middleName;

    public String getGivenName() {
        return givenName != null ? givenName : "";
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName != null ? familyName : "";
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getMiddleName() {
        return middleName != null ? middleName : "";
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
}
