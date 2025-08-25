package com.mohsolehuddin.vehicle_reservation.constant;

public class Message {
    private Message() {
    }

    public static final String REQUIRED_EMAIL = "Email is required";
    public static final String REQUIRED_PASSWORD = "Password is required";
    public static final String REQUIRED_DATA = "Data is required";

    public static final String INVALID_EMAIL = "Invalid email format";
    public static final String INVALID_PASSWORD = "Password must be at least 8 characters long, contain at least one uppercase letter, one lowercase letter, one digit, and one special character";

    public static final String LOGIN_SUCCESS = "Login successful";
    public static final String USER_CREATED = "User created";

    public static final String SUCCESS_FETCH = "Data Fetched";
    public static final String SUCCESS_DELETE = "Successfully deleted";
    public static final String DATA_NOT_FOUND = "Data Not Found";
    public static final String DATA_UPDATED = "Data Updated";
    public static final String DATA_CREATED = "Data Created";
}
