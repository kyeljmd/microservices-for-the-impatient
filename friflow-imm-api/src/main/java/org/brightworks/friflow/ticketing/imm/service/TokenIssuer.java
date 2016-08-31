package org.brightworks.friflow.ticketing.imm.service;

/**
 * Created by kyel on 8/14/2016.
 */
public interface TokenIssuer<TYPE> {

    TYPE issueToken(String userName);

}
