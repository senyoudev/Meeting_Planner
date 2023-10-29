package com.younesmeskafe.MeetingPlanner.adapters.config;

import com.younesmeskafe.MeetingPlanner.adapters.MeetingServiceImpl;
import com.younesmeskafe.MeetingPlanner.adapters.ports.MeetingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class Config {
    @Bean
    public MeetingService meetingService() {
        return new MeetingServiceImpl();
    }

}
