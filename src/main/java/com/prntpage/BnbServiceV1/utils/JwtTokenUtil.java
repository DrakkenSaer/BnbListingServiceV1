package com.prntpage.BnbServiceV1.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;


@Component
public class JwtTokenUtil implements Serializable {

    private final String issuer = "PrntPage";
    private final String audience = "BnbService";

    @Value("${jwt.secret}")
    private String secret;

    public String generateJwtBuilder(String auth) {
        Algorithm algorithm = Algorithm.HMAC512(secret);
        return JWT.create()
                .withIssuer(issuer)
                .withAudience(audience)
                .withClaim("auth", auth)
                .sign(algorithm);
    }

    public DecodedJWT verify(String token) {
        Algorithm algorithm = Algorithm.HMAC512(secret);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(issuer)
                .withAudience(audience)
                .build();
        return verifier.verify(token);
    }
}