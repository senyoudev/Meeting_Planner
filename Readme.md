# MeetingPlanner Application

## Overview

The MeetingPlanner application is a  meeting room management system designed to streamline the reservation process within an organization. It offers a range of features aimed at optimizing the allocation of meeting spaces, taking into account various constraints including COVID-19 safety measures, room capacities, and equipment availability.

## Key Features

- **Meeting Room Reservation:** Facilitates the booking of meeting rooms by providing an intuitive interface for employees to schedule their meetings efficiently.

- **COVID-19 Safety Measures:** Ensures that meeting rooms are available for booking only after undergoing a thorough cleaning process, in compliance with COVID-19 guidelines.

- **Capacity Management:** Manages the maximum occupancy of each meeting room, allowing only a specified percentage of the room's total capacity to be utilized at any given time.

- **Equipment Consideration:** Takes into account the availability of specific equipment in each room, enabling the selection of rooms that meet the requirements of different types of meetings.


# Hexagonal Architecture Overview

In this application, the Hexagonal Architecture is implemented as follows:

- **Core Logic (Hexagon):** This is where the main business logic resides. It includes entities, use cases, and services responsible for managing meetings and rooms.

- **Adapters:**
    - *API Adapter:* This module handles incoming HTTP requests and translates them into the appropriate format for processing by the core logic.
    - *Memory Adapter:* Manages data storage and retrieval, including interactions with the in-memory database.

## Why Hexagonal Architecture:

The Hexagonal Architecture, also known as Ports and Adapters, was chosen for this project to promote a clean and modular design. This architectural style isolates the core business logic from external concerns like UI, databases, and third-party integrations. This separation enhances maintainability, testability, and flexibility in the application.


## Finding the Best Room

To find the best room for a meeting, the application considers various factors:

- **Capacity:** Ensures the room can accommodate the required number of attendees, while adhering to COVID-19 capacity limits.

- **Meeting Type Compatibility:** Checks if the room supports the necessary equipment and configuration for the specific meeting type.

- **Time Slot Availability:** Verifies if the room is available during the requested time slot, accounting for cleaning time and operating hours.

- **COVID-19 Precautions:** Reserves a one-hour gap before the next booking for cleaning.

- **Day of the Week:** Ensures that reservations are only available on weekdays.

- **Weekend Exclusion:** Prevents bookings on weekends.


The application also prioritizes rooms based on their ability to host multiple meeting types versus specializing in one specific type. Rooms that can host multiple types are most likely to not be chosen if there is a room that can host only the choosen type.

## How to Run and Build the Project:

1. **Clone the Repository:**
    ```bash
    git clone https://github.com/yourusername/MeetingPlanner.git
    ```

2. **Navigate to the Project Directory:**
    ```bash
    cd MeetingPlanner
    ```

3. **Build and Run the Application:**
    ```bash
    ./mvnw spring-boot:run
    ```

   The application will start and be accessible at [http://localhost:8080](http://localhost:8080).


## How to Interact with the API

### Using Swagger

You can interact with the application using Swagger, a powerful API documentation tool. Follow these steps:

1. Navigate to the Swagger UI at [Swagger UI](http://localhost:8080/swagger-ui/index.html).
2. You will find One endpoint which aims to find the most suitable room for a meeting and book it.
3. Click on the `POST /api/v1/meetings` endpoint to get more information about how to use it.

**Endpoint:**

Or to interact with the application, you can send a POST request to the following API endpoint:

```json
POST http://localhost:8080/api/v1/meetings
```
## Request Body Example

```json
{
  "attendees": 1,
  "meetingType": "SPEC",
  "timeslot": "08:00-09:00",
  "reservationDate": "2023-10-30"
}
```
## Tests

The project includes a suite of unit and integration tests to validate the functionality of the codebase.

[Coverage Report](./report-coverage/index.html)