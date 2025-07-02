package com.borntocode.dev.tickets.Services.impl;

import com.borntocode.dev.tickets.Services.EventService;
import com.borntocode.dev.tickets.domain.CreateEventRequest;
import com.borntocode.dev.tickets.domain.entities.Event;
import com.borntocode.dev.tickets.domain.entities.TicketType;
import com.borntocode.dev.tickets.domain.entities.User;
import com.borntocode.dev.tickets.exceptions.UserNotFoundException;
import com.borntocode.dev.tickets.repositories.EventRepository;
import com.borntocode.dev.tickets.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    @Override
    public Event createEvent(UUID organizerId, CreateEventRequest event) {
        User organizer =  userRepository.findById(organizerId).orElseThrow(() -> new UserNotFoundException(String.format("User with ID %s not found", organizerId)));

        List<TicketType> ticketTypesToCreate =  event.getTicketTypes().stream().map(ticketType->{
            TicketType ticketTypeToCreate = new TicketType();
            ticketTypeToCreate.setName(ticketType.getName());
            ticketTypeToCreate.setPrice(ticketType.getPrice());
            ticketTypeToCreate.setDescription(ticketType.getDescription());
            ticketTypeToCreate.setTotalAvailable(ticketType.getTotalAvailable());
            return ticketTypeToCreate;
        }).toList();

        Event eventToCreate = new Event();
        eventToCreate.setName(event.getName());
        eventToCreate.setStart(event.getStart());
        eventToCreate.setEnd(event.getEnd());
        eventToCreate.setVenue(event.getVenue());
        eventToCreate.setSalesStart(eventToCreate.getSalesStart());
        eventToCreate.setSalesEnd(eventToCreate.getSalesEnd());
        eventToCreate.setStatus(eventToCreate.getStatus());
        eventToCreate.setOrganizer(organizer);
        eventToCreate.setTicketTypes(ticketTypesToCreate);

        return eventRepository.save(eventToCreate);

    }
}
