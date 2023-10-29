package com.younesmeskafe.MeetingPlanner.domain.output;


import com.younesmeskafe.MeetingPlanner.domain.Meeting;

import java.util.List;

public interface MeetingRepository {
    List<Meeting> findAll();

    Meeting findById(long id);

    void save(Meeting meeting);

    void delete(long id);
}
