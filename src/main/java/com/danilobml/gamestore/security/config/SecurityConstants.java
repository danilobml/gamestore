package com.danilobml.gamestore.security.config;

import org.springframework.core.env.Environment;
import com.auth0.jwt.algorithms.Algorithm;


public class SecurityConstants {
    public static final String LOGIN_PATH = "/users/login";
    public static final int TOKEN_EXPIRATION = 60 * 60 * 1000; // 1 Hour

    public static String JWT_SECRET;
    public static Algorithm ALGORITHM;

    public static void initialize(Environment env) {
        JWT_SECRET = env.getProperty("security.jwt.secret", "defaultSecret");
        ALGORITHM = Algorithm.HMAC512(JWT_SECRET);
    }
}
