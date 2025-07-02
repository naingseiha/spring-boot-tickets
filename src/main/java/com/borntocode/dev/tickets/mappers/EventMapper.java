package com.borntocode.dev.tickets.mappers;

import com.borntocode.dev.tickets.domain.CreateEventRequest;
import com.borntocode.dev.tickets.domain.CreateTicketTypeRequest;
import com.borntocode.dev.tickets.domain.dtos.CreateEventRequestDto;
import com.borntocode.dev.tickets.domain.dtos.CreateEventResponseDto;
import com.borntocode.dev.tickets.domain.dtos.CreateTicketTypeRequestDto;

import com.borntocode.dev.tickets.domain.entities.Event;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventMapper {

    CreateTicketTypeRequest fromDto(CreateTicketTypeRequestDto dto);

    CreateEventRequest fromDto(CreateEventRequestDto dto);

    CreateEventResponseDto toDto(Event event);
}
