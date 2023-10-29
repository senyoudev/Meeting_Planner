package com.younesmeskafe.MeetingPlanner.domain;

public class MeetingNotFoundException extends RuntimeException {
    public MeetingNotFoundException(String message) {
        super(message);
    }
}