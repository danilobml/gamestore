package com.danilobml.gamestore.security.config;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;


public class JWTUtil {
    
    public static String generateToken(String username) {
        return JWT.create()
            .withSubject(username)
            .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.TOKEN_EXPIRATION))
            .sign(Algorithm.HMAC512(SecurityConstants.JWT_SECRET));
    }

    public static String extractUsername(String token) {
        return JWT.require(SecurityConstants.ALGORITHM)
            .build()
            .verify(token)
            .getSubject();
    }

    public static boolean validateToken(String token, String username) {
        return username.equals(extractUsername(token));
    }

}
