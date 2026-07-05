package com.aston.notification.dto;

public class UserEventDto {

    private String email;
    private String operation; // "CREATED" или "DELETED"

    public UserEventDto() {}

    public UserEventDto(String email, String operation) {
        this.email = email;
        this.operation = operation;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getOperation() { return operation; }
    public void setOperation(String operation) { this.operation = operation; }

    @Override
    public String toString() {
        return "UserEventDto{" +
                "email='" + email + '\'' +
                ", operation='" + operation + '\'' +
                '}';
    }
}