package org.brightworks.friflow.ticketing.imm.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * Created by kyel on 8/14/2016.
 */
@Service
public class JwtTokenIssuerService implements TokenIssuer<String>{

    @Value("${jwt.security.key}")
    private String key;

    @Value("${ws.issuer}")
    private String issuer;

    static final long ONE_MINUTE_IN_MILLIS=60000;

    static final long TOKEN_DURATION_IN_MIN=30L;

    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    @Override
    public String issueToken(String userName) {

        final long nowMillis = System.currentTimeMillis();
        final long expMillis = nowMillis + (ONE_MINUTE_IN_MILLIS * TOKEN_DURATION_IN_MIN);

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(key);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        return Jwts
                .builder()
                .setIssuedAt(new Date(nowMillis))
                .setExpiration(new Date(expMillis))
                .setSubject(userName)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey).compact();

    }
}
