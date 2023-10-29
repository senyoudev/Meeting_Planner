package com.younesmeskafe.MeetingPlanner.adapters.memory;

import com.younesmeskafe.MeetingPlanner.domain.Equipment;
import com.younesmeskafe.MeetingPlanner.domain.Room;
import com.younesmeskafe.MeetingPlanner.domain.output.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoomRepositoryImplTest {

    private RoomRepository roomRepository;

    @BeforeEach
    void setUp() {
        roomRepository = new RoomRepositoryImpl();
    }

    @Test
    void findAll() {
        List<Room> rooms = roomRepository.findAll();
        assertEquals(12, rooms.size());
    }

    @Test
    void findById_ExistingId_ReturnsRoom() {
        Room room = roomRepository.findById(1001);
        assertNotNull(room);
        assertEquals(1001, room.getId());
        assertEquals("E1001", room.getName());
    }

    @Test
    void findById_NonExistingId_ReturnsNull() {
        Room room = roomRepository.findById(9999);
        assertNull(room);
    }


    @Test
    void save() {
        Room room = new Room(9999, "TestRoom", new HashSet<>(Collections.singletonList(Equipment.NEANT)), 10);
        roomRepository.save(room);

        List<Room> rooms = roomRepository.findAll();
        assertEquals(13, rooms.size());
    }

    @Test
    void delete() {
        roomRepository.delete(1001);

        List<Room> rooms = roomRepository.findAll();
        assertEquals(11, rooms.size());
        assertNull(roomRepository.findById(1001));
    }
}