package com.ticketless.serviceimplimentation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.ticketless.model.Place;
import com.ticketless.repository.PlaceRepo;
import com.ticketless.requestdto.StateCitySearch;
import com.ticketless.requestdto.VisitorRequestDto;
import com.ticketless.resposedto.PlaceResponseDto;
import com.ticketless.service.BookingService;
import com.ticketless.service.PlaceService;

@Component
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private PlaceRepo placeRepo;
    
    @Autowired
    private BookingService bookingService;

    @Override
    public List<PlaceResponseDto> getAllPlaceyStateCity(StateCitySearch stateCitySearch, int pageNo) {

        if (stateCitySearch.getPlaceName() == null) {
            stateCitySearch.setPlaceName("");
        }
        if (stateCitySearch.getCityName() == null) {
            stateCitySearch.setCityName("");
        }
        if (stateCitySearch.getStateName() == null) {
            stateCitySearch.setStateName("");
        }

        List<PlaceResponseDto> placeResponseDtos;
        try {
            Pageable pageable = PageRequest.of(pageNo - 1, 10);
            List<Place> response = placeRepo.getPlaceCityState(stateCitySearch.getCityName(),
                    stateCitySearch.getStateName(), stateCitySearch.getPlaceName(), pageable).getContent();
            placeResponseDtos = converToDto(response);
            return placeResponseDtos;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private List<PlaceResponseDto> converToDto(List<Place> response) {
        List<PlaceResponseDto> ansDtos = new ArrayList<>();

        for (Place place : response) {
            ansDtos.add(convertorPlaceDto(place));
        }
        return ansDtos;
    }

    
    @Override
    public PlaceResponseDto getPlaceById(Long placeId) {
        try {
            return convertorPlaceDto(placeRepo.getById(Long.valueOf(placeId)));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private PlaceResponseDto convertorPlaceDto(Place place) {
        PlaceResponseDto placeResponseDto = new PlaceResponseDto();
        placeResponseDto.setCity(place.getCity());
        placeResponseDto.setEndTime(place.getEndTime());
        placeResponseDto.setLatitude(place.getLatitude());
        placeResponseDto.setLongitude(place.getLongitude());
        placeResponseDto.setPicUrl(place.getPicUrl());
        placeResponseDto.setPlaceDescription(place.getPlaceDescription());
        placeResponseDto.setPlaceId(place.getPlaceId());
        placeResponseDto.setPlaceName(place.getPlaceName());
        placeResponseDto.setPriceAdult(place.getPriceAdult());
        placeResponseDto.setPriceChild(place.getPriceChild());
        placeResponseDto.setStartTime(place.getStartTime());
        placeResponseDto.setState(place.getState());
        return placeResponseDto;
    }

    @Override
    public Place getPlaceEnById(Long placeId) {
       Optional<Place> findById = placeRepo.findById(placeId);
       if(findById.isPresent()) return findById.get();
        return null;
    }

    @Override
    public Long getTotalVisitor(VisitorRequestDto requestDto) {
        
        Long ans =  bookingService.getAllBookings(requestDto);
        
        
        return ans;
    }

}






























