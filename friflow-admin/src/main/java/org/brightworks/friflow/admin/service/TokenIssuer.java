package org.brightworks.friflow.admin.service;

/**
 * Created by kyel on 8/14/2016.
 */
public interface TokenIssuer<TYPE> {

    TYPE issueToken(String userName);

}
