package com.younesmeskafe.MeetingPlanner.domain;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


class RoomTest {

    @Test
    void canAccommodateMeeting_VCANDRS() {
        var room = new Room(1001, "E1001", new HashSet<>(Arrays.asList(Equipment.SCREEN, Equipment.WEBCAM, Equipment.PROJECTOR)), 13);
        assertTrue(room.canAccommodateMeeting(MeetingType.VC));
        assertTrue(room.canAccommodateMeeting(MeetingType.RS));
        assertFalse(room.canAccommodateMeeting(MeetingType.RC));
        assertFalse(room.canAccommodateMeeting(MeetingType.SPEC));
    }

    @Test
    void canAccommodateMeeting_SPECANDRS() {
        var room = new Room(1001, "E1001", new HashSet<>(Arrays.asList(Equipment.WHITEBOARD)), 13);
        assertTrue(room.canAccommodateMeeting(MeetingType.SPEC));
        assertFalse(room.canAccommodateMeeting(MeetingType.VC));
        assertTrue(room.canAccommodateMeeting(MeetingType.RS));
        assertFalse(room.canAccommodateMeeting(MeetingType.RC));
    }

    @Test
    void canAccommodateMeeting_RCANDRSANDSPEC() {
        var room = new Room(1001, "E1001", new HashSet<>(Arrays.asList(Equipment.SCREEN, Equipment.WHITEBOARD, Equipment.PROJECTOR)), 13);
        assertFalse(room.canAccommodateMeeting(MeetingType.VC));
        assertTrue(room.canAccommodateMeeting(MeetingType.RS));
        assertTrue(room.canAccommodateMeeting(MeetingType.RC));
        assertTrue(room.canAccommodateMeeting(MeetingType.SPEC));
    }


    @Test
    void hasNo_CapacityForAttendes_4() {
        var room = new Room(1001, "E1001", new HashSet<>(Arrays.asList(Equipment.SCREEN, Equipment.WHITEBOARD, Equipment.PROJECTOR)), 5);
        assertFalse(room.hasCapacityFor(4));
    }

    @Test
    void hasCapacityForAttendes_3() {
        var room = new Room(1001, "E1001", new HashSet<>(Arrays.asList(Equipment.SCREEN, Equipment.WHITEBOARD, Equipment.PROJECTOR)), 5);
        assertTrue(room.hasCapacityFor(3));
    }

    @Test
    void isValidTimeslot() {
        var room = new Room(1001, "E1001", new HashSet<>(Arrays.asList(Equipment.SCREEN, Equipment.WHITEBOARD, Equipment.PROJECTOR)), 5);
        Boolean isValidTimeSlot = room.isValidTimeslot("08:00-09:00");
        assertTrue(isValidTimeSlot);
    }

    @Test
    void isNoT_ValidTimeslot() {
        var room = new Room(1001, "E1001", new HashSet<>(Arrays.asList(Equipment.SCREEN, Equipment.WHITEBOARD, Equipment.PROJECTOR)), 5);
        Boolean isNoValidTimeSlot = room.isValidTimeslot("08:00-10:00");
        assertFalse(isNoValidTimeSlot);
    }

    @Test
    void isValidTimeslot_Before() {
        var room = new Room(1001, "E1001", new HashSet<>(Arrays.asList(Equipment.SCREEN, Equipment.WHITEBOARD, Equipment.PROJECTOR)), 5);
        Boolean isValidTimeSlot = room.isValidTimeslot("07:00-08:00");
        assertFalse(isValidTimeSlot);
    }


    @Test
    void isValidTimeslot_After() {
        var room = new Room(1001, "E1001", new HashSet<>(Arrays.asList(Equipment.SCREEN, Equipment.WHITEBOARD, Equipment.PROJECTOR)), 5);
        Boolean isValidTimeSlot = room.isValidTimeslot("20:00-21:00");
        assertFalse(isValidTimeSlot);
    }

    @Test
    void isValidTimeslot_NotRightSyntax() {
        var room = new Room(1001, "E1001", new HashSet<>(Arrays.asList(Equipment.SCREEN, Equipment.WHITEBOARD, Equipment.PROJECTOR)), 5);
        Boolean isValidTimeSlot = room.isValidTimeslot("20:00/21:00");
        assertFalse(isValidTimeSlot);
    }

    @Test
    void isValidTimeslot_ShouldThrowExcep() {
        var room = new Room(1001, "E1001", new HashSet<>(Arrays.asList(Equipment.SCREEN, Equipment.WHITEBOARD, Equipment.PROJECTOR)), 5);
        Boolean isValidTimeSlot = room.isValidTimeslot("wo-rd");
        assertFalse(isValidTimeSlot);
    }

    @Test
    void isValidReservationDate() {
        var room = new Room(1001, "E1001", new HashSet<>(Arrays.asList(Equipment.SCREEN, Equipment.WHITEBOARD, Equipment.PROJECTOR)), 5);
        LocalDate date = LocalDate.of(2023, 10, 27);
        Boolean isValidReservationDate = room.isValidReservationDate(date);
        assertTrue(isValidReservationDate);
    }

    @Test
    void isNOT_ValidReservationDate() {
        var room = new Room(1001, "E1001", new HashSet<>(Arrays.asList(Equipment.SCREEN, Equipment.WHITEBOARD, Equipment.PROJECTOR)), 5);
        LocalDate date = LocalDate.of(2023, 10, 28);
        Boolean isNotValidReservationDate = room.isValidReservationDate(date);
        assertFalse(isNotValidReservationDate);
    }

    @Test
    void testDetermineMeetingTypes() throws Exception {
        Set<Equipment> equipements = new HashSet<>(Arrays.asList(Equipment.SCREEN, Equipment.WEBCAM, Equipment.PROJECTOR));
        var room = new Room(1001, "E1001", equipements, 5);


        Method method = Room.class.getDeclaredMethod("determineSupportedMeetingTypes", Set.class);
        method.setAccessible(true);

        Set<MeetingType> supportedMeetingTypes = (Set<MeetingType>) method.invoke(room, equipements);

        assertTrue(supportedMeetingTypes.contains(MeetingType.VC));
        assertTrue(supportedMeetingTypes.contains(MeetingType.RS));
        assertFalse(supportedMeetingTypes.contains(MeetingType.RC));
        assertFalse(supportedMeetingTypes.contains(MeetingType.SPEC));

    }
}