package com.danilobml.gamestore.security;

import org.springframework.beans.factory.annotation.Value;

public class SecurityConstants {

    public static final String LOGIN_PATH = "/users/login";

    public static final int TOKEN_EXPIRATION = 60 * 60 * 1000; // 1 Hour

    @Value("${application.jwt.secret}")
    public static String JWT_SECRET;

}
