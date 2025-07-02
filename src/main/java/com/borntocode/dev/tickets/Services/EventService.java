package com.borntocode.dev.tickets.Services;

import com.borntocode.dev.tickets.domain.CreateEventRequest;
import com.borntocode.dev.tickets.domain.entities.Event;

import java.util.UUID;

public interface EventService {
    Event createEvent(UUID organizerId, CreateEventRequest event);
}
