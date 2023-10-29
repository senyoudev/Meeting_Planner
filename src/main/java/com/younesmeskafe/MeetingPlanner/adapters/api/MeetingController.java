package com.younesmeskafe.MeetingPlanner.adapters.api;

import com.younesmeskafe.MeetingPlanner.adapters.ports.MeetingService;
import com.younesmeskafe.MeetingPlanner.domain.MeetingNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/meetings")
public class MeetingController {

    private final MeetingService meetingService;

    Logger logger = LoggerFactory.getLogger(MeetingController.class);


    public MeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }


    @PostMapping
    public ResponseEntity<String> scheduleMeeting(@RequestBody MeetingRequest request) {
        try {
            LocalDate reservationDate = LocalDate.parse(request.getReservationDate());
            String meetingRoom = meetingService.findBestRoom(request.getAttendees(), request.getMeetingType(), request.getTimeslot(), reservationDate);
            logger.info("Meeting scheduled in room: {}", meetingRoom);
            return ResponseEntity.ok("Scheduled in room: " + meetingRoom);
        } catch (MeetingNotFoundException e) {
            logger.warn("Meeting not found exception: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            logger.error("An error occurred: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }
}
