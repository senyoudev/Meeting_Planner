package com.younesmeskafe.MeetingPlanner.adapters.memory;

import com.younesmeskafe.MeetingPlanner.domain.Meeting;
import com.younesmeskafe.MeetingPlanner.domain.MeetingType;
import com.younesmeskafe.MeetingPlanner.domain.Room;
import com.younesmeskafe.MeetingPlanner.domain.output.MeetingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MeetingRepositoryImplTest {
    private MeetingRepository meetingRepository;

    @BeforeEach
    void setUp() {
        this.meetingRepository = new MeetingRepositoryImpl();
    }

    @Test
    void findAll() {
        List<Meeting> meetings = meetingRepository.findAll();
        assertNotNull(meetings);
        assertEquals(1, meetings.size());
    }

    @Test
    void findById_existingMeeting_returnMeeting() {
        Meeting existingMeeting = meetingRepository.findById(1);
        assertNotNull(existingMeeting);
        assertEquals(1, existingMeeting.getId());
    }

    @Test
    void findById_nonExistingMeeting_returnNull() {
        Meeting nonExistingMeeting = meetingRepository.findById(2);
        assertNull(nonExistingMeeting);
    }

    @Test
    void save() {
        Meeting newMeeting = new Meeting(2, LocalDate.now(), "09:00-10:00", MeetingType.RC, 8, new Room(1002, "E1002", new HashSet<>(), 15));
        meetingRepository.save(newMeeting);
        assertEquals(2, meetingRepository.findAll().size());
    }

    @Test
    void delete() {
        meetingRepository.delete(1);
        assertEquals(0, meetingRepository.findAll().size());
    }
}