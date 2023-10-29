package com.younesmeskafe.MeetingPlanner.adapters;

import com.younesmeskafe.MeetingPlanner.adapters.ports.MeetingService;
import com.younesmeskafe.MeetingPlanner.domain.MeetingNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MeetingServiceImplTest {

    @Autowired
    private MeetingService meetingService;

    @Test
    public void findBestRoom_testScenario1() {
        int attendees = 8;
        String meetingType = "VC";
        String timeslot = "09:00-10:00";
        LocalDate reservationDate = LocalDate.parse("2023-10-30");
        assertThrows(MeetingNotFoundException.class, () -> {
            meetingService.findBestRoom(attendees, meetingType, timeslot, reservationDate);
        });
    }

    @Test
    public void findBestRoom_testScenario2() {
        int attendees = 6;
        String meetingType = "VC";
        String timeslot = "09:00-10:00";
        LocalDate reservationDate = LocalDate.parse("2023-10-30");
        String result = meetingService.findBestRoom(attendees, meetingType, timeslot, reservationDate);
        assertEquals("E3003", result);
    }

    @Test
    public void findBestRoom_testScenario3() {
        int attendees = 6;
        String meetingType = "VC";
        String timeslot = "10:00-11:00";
        LocalDate reservationDate = LocalDate.parse("2023-10-30");
        assertThrows(MeetingNotFoundException.class, () -> {
            meetingService.findBestRoom(attendees, meetingType, timeslot, reservationDate);
        });
    }

    @Test
    public void findBestRoom_testScenario4() {
        int attendees = 4;
        String meetingType = "RC";
        String timeslot = "11:00-12:00";
        LocalDate reservationDate = LocalDate.parse("2023-10-30");
        String result = meetingService.findBestRoom(attendees, meetingType, timeslot, reservationDate);
        assertEquals("E3001", result);
    }

    @Test
    public void findBestRoom_testScenario5() {
        int attendees = 2;
        String meetingType = "RS";
        String timeslot = "11:00-12:00";
        LocalDate reservationDate = LocalDate.parse("2023-10-30");
        String result = meetingService.findBestRoom(attendees, meetingType, timeslot, reservationDate);
        assertEquals("E2001", result);
    }

    @Test
    public void findBestRoom_testScenario6() {
        int attendees = 9;
        String meetingType = "SPEC";
        String timeslot = "11:00-12:00";
        LocalDate reservationDate = LocalDate.parse("2023-10-30");
        assertThrows(MeetingNotFoundException.class, () -> {
            meetingService.findBestRoom(attendees, meetingType, timeslot, reservationDate);
        });
    }

    @Test
    public void findBestRoom_testScenario7() {
        int attendees = 7;
        String meetingType = "RC";
        String timeslot = "09:00-10:00";
        LocalDate reservationDate = LocalDate.parse("2023-10-30");
        String result = meetingService.findBestRoom(attendees, meetingType, timeslot, reservationDate);
        assertEquals("E3001", result);
    }

    @Test
    public void findBestRoom_testScenario8() {
        int attendees = 9;
        String meetingType = "VC";
        String timeslot = "08:00-09:00";
        LocalDate reservationDate = LocalDate.parse("2023-10-30");
        assertThrows(MeetingNotFoundException.class, () -> {
            meetingService.findBestRoom(attendees, meetingType, timeslot, reservationDate);
        });    }

    @Test
    public void findBestRoom_testScenario9() {
        int attendees = 10;
        String meetingType = "SPEC";
        String timeslot = "08:00-09:00";
        LocalDate reservationDate = LocalDate.parse("2023-10-30");
        assertThrows(MeetingNotFoundException.class, () -> {
            meetingService.findBestRoom(attendees, meetingType, timeslot, reservationDate);
        });    }

    @Test
    public void findBestRoom_testScenario10() {
        int attendees = 10;
        String meetingType = "SPEC";
        String timeslot = "08:00-09:00";
        LocalDate reservationDate = LocalDate.parse("2023-10-30");
        assertThrows(MeetingNotFoundException.class, () -> {
            meetingService.findBestRoom(attendees, meetingType, timeslot, reservationDate);
        });    }

    @Test
    public void findBestRoom_testScenario11() {
        int attendees = 5;
        String meetingType = "SPEC";
        String timeslot = "09:00-10:00";
        LocalDate reservationDate = LocalDate.parse("2023-10-30");
        String result = meetingService.findBestRoom(attendees, meetingType, timeslot, reservationDate);
        assertEquals("E2004", result);
    }

    @Test
    public void findBestRoom_testScenario12() {
        int attendees = 4;
        String meetingType = "RS";
        String timeslot = "09:00-10:00";
        LocalDate reservationDate = LocalDate.parse("2023-10-30");
        String result = meetingService.findBestRoom(attendees, meetingType, timeslot, reservationDate);
        assertEquals("E2003", result);
    }


    }