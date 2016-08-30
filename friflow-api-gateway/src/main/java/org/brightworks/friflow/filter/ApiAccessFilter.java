package org.brightworks.friflow.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


public class ApiAccessFilter implements Filter {

    @Value("${jwt.security.key}")
    private String jwtKey;

    public ApiAccessFilter(String apiKey) {
        this.jwtKey = apiKey;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        String token = request.getHeader("Authorization");

        if(token != null) {
            if(isTokenValid(token)) {
                filterChain.doFilter(request,response);
            }else {
                throw new TokenException("Token is Either Expired or Modified");
            }
        }else {
            throw new TokenException("You need the access token to access this resource");
        }
    }

    private boolean isTokenValid(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtKey)
                    .parseClaimsJws(token).getBody();

            Date now = new Date(System.currentTimeMillis());

            if(claims.getExpiration().before(now))
                return false;
        }catch (SignatureException se) {
            return false;
        }catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public void destroy() {}
}
