package org.brightworks.friflow.filter;

import javax.servlet.ServletException;

/**
 * Created by kyel on 8/15/2016.
 */
public class TokenException extends ServletException {

    public TokenException(String mes){
        super(mes);
    }
}
