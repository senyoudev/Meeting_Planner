package com.younesmeskafe.MeetingPlanner.domain;

import java.time.LocalDate;
import java.util.Objects;

public class Meeting {
    private long id;
    private LocalDate date;
    private String timeslot;
    private MeetingType type;
    private int attendees;
    private Room room;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return id == meeting.id && attendees == meeting.attendees && Objects.equals(date, meeting.date) && Objects.equals(timeslot, meeting.timeslot) && type == meeting.type && Objects.equals(room, meeting.room);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, timeslot, type, attendees, room);
    }

    public Meeting(long id, LocalDate date, String timeslot, MeetingType type, int attendees, Room room) {
        this.id = id;
        this.date = date;
        this.timeslot = timeslot;
        this.type = type;
        this.attendees = attendees;
        this.room = room;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTimeslot(String timeslot) {
        this.timeslot = timeslot;
    }

    public String getTimeslot() {
        return timeslot;
    }

    public MeetingType getType() {
        return type;
    }

    public void setType(MeetingType type) {
        this.type = type;
    }

    public int getAttendees() {
        return attendees;
    }

    public void setAttendees(int attendees) {
        this.attendees = attendees;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }


}