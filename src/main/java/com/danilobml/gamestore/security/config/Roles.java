package com.danilobml.gamestore.security.config;

public enum Roles {
    USER("USER"),
    ADMIN("ADMIN");

    private final String role;

    Roles(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public String getSpringRole() {
        return "ROLE_" + role;
    }
}
