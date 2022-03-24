package com.example.springbootdemo.model;

public enum Permission {
    USERS_READ("users:read"),
    USERS_WRITE("users:write");

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

    private final String permission;
}
