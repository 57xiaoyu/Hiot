package com.cch.hiot2.entity;

public class LoginEntity {
    private String tokenValue;
    private String uuid;

    public String getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "LoginEntity{" +
                "tokenValue='" + tokenValue + '\'' +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}














