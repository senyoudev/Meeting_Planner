package com.younesmeskafe.MeetingPlanner.domain.output;

import com.younesmeskafe.MeetingPlanner.domain.Room;

import java.util.List;



public interface RoomRepository {
    List<Room> findAll();
    Room findById(long id);
    void save(Room room);
    void delete(long id);
}
