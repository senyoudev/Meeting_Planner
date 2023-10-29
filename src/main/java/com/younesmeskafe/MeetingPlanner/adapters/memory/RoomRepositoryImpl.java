package com.younesmeskafe.MeetingPlanner.adapters.memory;

import com.younesmeskafe.MeetingPlanner.domain.Equipment;
import com.younesmeskafe.MeetingPlanner.domain.Room;
import com.younesmeskafe.MeetingPlanner.domain.output.RoomRepository;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class RoomRepositoryImpl implements RoomRepository {


    public List<Room> rooms = new ArrayList<>(Arrays.asList(
            new Room(1001, "E1001", new HashSet<>(Collections.singletonList(Equipment.NEANT)), 23),
            new Room(1002, "E1002", new HashSet<>(Arrays.asList(Equipment.SCREEN)), 10),
            new Room(1003, "E1003", new HashSet<>(Collections.singletonList(Equipment.PROJECTOR)), 8),
            new Room(1004, "E1004", new HashSet<>(Collections.singletonList(Equipment.WHITEBOARD)), 4),
            new Room(2001, "E2001", new HashSet<>(Collections.singletonList(Equipment.NEANT)), 4),
            new Room(2002, "E2002", new HashSet<>(Arrays.asList(Equipment.SCREEN, Equipment.WEBCAM)), 15),
            new Room(2003, "E2003", new HashSet<>(Collections.singletonList(Equipment.NEANT)), 7),
            new Room(2004, "E2004", new HashSet<>(Collections.singletonList(Equipment.WHITEBOARD)), 9),
            new Room(3001, "E3001", new HashSet<>(Arrays.asList(Equipment.SCREEN, Equipment.WHITEBOARD, Equipment.PROJECTOR)), 13),
            new Room(3002, "E3002", new HashSet<>(Collections.singletonList(Equipment.NEANT)), 8),
            new Room(3003, "E3003", new HashSet<>(Arrays.asList(Equipment.SCREEN, Equipment.PROJECTOR, Equipment.WEBCAM)), 9),
            new Room(3004, "E3004", new HashSet<>(Collections.singletonList(Equipment.NEANT)), 4)
    ));

    private long nextId = 1;

    @Override
    public List<Room> findAll() {
        return new ArrayList<>(rooms);
    }

    @Override
    public Room findById(long id) {
        for (Room room : rooms) {
            if (room.getId() == id) {
                return room;
            }
        }
        return null;
    }

    @Override
    public void save(Room room) {
        room.setId(nextId++);
        rooms.add(room);
    }

    @Override
    public void delete(long id) {
        rooms.removeIf(room -> room.getId() == id);
    }
}
