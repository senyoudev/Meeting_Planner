package com.younesmeskafe.MeetingPlanner.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MeetingNotFoundExceptionTest {
    @Test
    void testConstructor() {
        String message = "Meeting not found";
        MeetingNotFoundException exception = new MeetingNotFoundException(message);

        assertEquals(message, exception.getMessage());
    }
}