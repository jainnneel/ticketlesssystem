package com.ticketless.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ticketless.model.Place;
import com.ticketless.requestdto.StateCitySearch;
import com.ticketless.resposedto.PlaceResponseDto;

@Service
public interface PlaceService {

    List<PlaceResponseDto> getAllPlaceyStateCity(StateCitySearch stateCitySearch, int pageNo);

    PlaceResponseDto getPlaceById(Long placeId);

    Place getPlaceEnById(Long placeId);

}
