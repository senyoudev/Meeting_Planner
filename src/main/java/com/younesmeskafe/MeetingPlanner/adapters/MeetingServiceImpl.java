package com.younesmeskafe.MeetingPlanner.adapters;

import com.younesmeskafe.MeetingPlanner.adapters.ports.MeetingService;
import com.younesmeskafe.MeetingPlanner.domain.*;
import com.younesmeskafe.MeetingPlanner.domain.output.MeetingRepository;
import com.younesmeskafe.MeetingPlanner.domain.output.RoomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MeetingServiceImpl implements MeetingService {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private MeetingRepository meetingRepository;

    Logger logger = LoggerFactory.getLogger(MeetingServiceImpl.class);


    @Override
    public String findBestRoom(int attendees, String meetingType, String timeslot, LocalDate reservationDate) {
        MeetingType meetingTypEnum = MeetingType.valueOf(meetingType);
        Comparator<Room> roomComparator = Comparator
                .comparingInt((Room room) -> room.getSupportedMeetingTypes().size())
                .thenComparingInt(Room::getCapacity);
        List<Room> availableRooms = roomRepository.findAll()
            .stream()
                .filter(room -> room.hasCapacityFor(attendees))
                .filter(room -> room.canAccommodateMeeting(meetingTypEnum))
                .filter(room -> room.isValidTimeslot(timeslot))
                .filter(room -> isAvailableAt(timeslot,room,reservationDate))
                .filter(room -> room.isValidReservationDate(reservationDate))
                .sorted(roomComparator)
                .collect(Collectors.toList());

        if (availableRooms.isEmpty()) {
            throw new MeetingNotFoundException("No suitable room found.");
        }
        Meeting meeting = new Meeting(meetingRepository.findAll().size(),reservationDate, timeslot, meetingTypEnum, attendees, availableRooms.get(0));

        meetingRepository.save(meeting);
        logger.info("Meeting saved. Total meetings: {}", meetingRepository.findAll().size());
        return availableRooms.get(0).getName();
    }

    public boolean isAvailableAt(String timeslot,Room room,LocalDate reservationDate){
        List<Meeting> meetings = meetingRepository.findAll();

        for (Meeting meeting : meetings) {
            if (meeting.getRoom().equals(room)) {
                String meetingTimeslot = meeting.getTimeslot();


                if (meeting.getDate().equals(reservationDate) && meetingTimeslot.equals(timeslot)) {
                    return false; // Room is already booked at this timeslot
                }

                // Check if there's at least a 1-hour gap before the next reservation
                if (meetingTimeslot.endsWith(timeslot.split("-")[0]) || meetingTimeslot.startsWith(timeslot.split("-")[1])) {
                    return false; // Less than 1-hour gap
                }
            }
        }

        return true;
    }






}