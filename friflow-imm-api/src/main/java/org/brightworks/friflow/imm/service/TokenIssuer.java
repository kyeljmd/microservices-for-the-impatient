package org.brightworks.friflow.imm.service;

/**
 * Created by kyel on 8/14/2016.
 */
public interface TokenIssuer<TYPE> {

    TYPE issueToken(String userName);

}
