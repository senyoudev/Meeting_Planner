package com.younesmeskafe.MeetingPlanner.adapters.memory;

import com.younesmeskafe.MeetingPlanner.domain.Equipment;
import com.younesmeskafe.MeetingPlanner.domain.Meeting;
import com.younesmeskafe.MeetingPlanner.domain.MeetingType;
import com.younesmeskafe.MeetingPlanner.domain.Room;
import com.younesmeskafe.MeetingPlanner.domain.output.MeetingRepository;
import org.springframework.stereotype.Component;


import java.time.LocalDate;
import java.util.*;

@Component
public class MeetingRepositoryImpl implements MeetingRepository {
    LocalDate date = LocalDate.of(2023, 10, 27);
    private List<Meeting> meetings = new ArrayList<>(Arrays.asList(
            new Meeting(1, date, "08:00-09:00", MeetingType.RS, 10, new Room(1001, "E1001", new HashSet<>(Collections.singletonList(Equipment.NEANT)), 23))
    ));


    private long nextId = 1;


    @Override
    public List<Meeting> findAll() {
        return new ArrayList<>(meetings);
    }

    @Override
    public Meeting findById(long id) {
        for (Meeting meeting : meetings) {
            if (meeting.getId() == id) {
                return meeting;
            }
        }
        return null;
    }

    @Override
    public void save(Meeting meeting) {
        meeting.setId(nextId++);
        meetings.add(meeting);
    }

    @Override
    public void delete(long id) {
        meetings.removeIf(meeting -> meeting.getId() == id);
    }
}
