package com.younesmeskafe.MeetingPlanner.adapters.ports;


import java.time.LocalDate;

public interface MeetingService {
    String findBestRoom(int attendees, String meetingType, String timeslot, LocalDate reservationDate);
}
