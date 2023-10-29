package com.younesmeskafe.MeetingPlanner.domain;


import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Room {
    private long id;

    private String name;
    private int capacity;

    private Set<MeetingType> supportedMeetingTypes;


    public Room(long id, String name, Set<Equipment> equipment, int capacity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.supportedMeetingTypes = determineSupportedMeetingTypes(equipment);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }


    public Set<MeetingType> getSupportedMeetingTypes() {
        return supportedMeetingTypes;
    }

    public void setSupportedMeetingTypes(Set<MeetingType> supportedMeetingTypes) {
        this.supportedMeetingTypes = supportedMeetingTypes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id == room.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    private Set<MeetingType> determineSupportedMeetingTypes(Set<Equipment> equipment) {
        Set<MeetingType> supportedMeetingTypes = new HashSet<>();

        // Check if the room has the required equipment for each meeting type
        if (equipment.contains(Equipment.SCREEN) && equipment.contains(Equipment.PROJECTOR) && equipment.contains(Equipment.WEBCAM)) {
            supportedMeetingTypes.add(MeetingType.VC);
        }

        if (equipment.contains(Equipment.WHITEBOARD)) {
            supportedMeetingTypes.add(MeetingType.SPEC);
        }

        if (capacity > 3) {
            supportedMeetingTypes.add(MeetingType.RS);
        }

        if (equipment.contains(Equipment.SCREEN) && equipment.contains(Equipment.WHITEBOARD) && equipment.contains(Equipment.PROJECTOR)) {
            supportedMeetingTypes.add(MeetingType.RC);
        }

        return supportedMeetingTypes;
    }

    public boolean canAccommodateMeeting(MeetingType meetingType) {
        return supportedMeetingTypes.contains(meetingType);
    }


    public boolean hasCapacityFor(int attendees) {
        return attendees <= (int) (capacity * 0.7);
    }

    public boolean isValidTimeslot(String timeslot) {
        // Accepted timeslot format is "hh:mm-hh:mm" (e.g., "08:00-20:00")
        String[] parts = timeslot.split("-");
        if (parts.length != 2) {
            return false;
        }

        String startTime = parts[0];
        String endTime = parts[1];

        try {
            LocalTime start = LocalTime.parse(startTime);
            LocalTime end = LocalTime.parse(endTime);

            // Ensure the timeslot is  one hour in length or less
            if (Duration.between(start, end).toMinutes() > 60) {
                return false;
            }

            // Check if start time is after or equal to 8:00 AM
            if (start.isBefore(LocalTime.of(8, 0))) {
                return false;
            }

            // Check if end time is before or equal to 8:00 PM
            if (end.isAfter(LocalTime.of(20, 0))) {
                return false;
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isValidReservationDate(LocalDate reservationDate) {
        // Check if the reservation date is a weekend
        DayOfWeek dayOfWeek = reservationDate.getDayOfWeek();
        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            return false;
        }
        return true;
    }


}