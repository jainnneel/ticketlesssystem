package com.ticketless.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ticketless.requestdto.StateCitySearch;
import com.ticketless.resposedto.PlaceResponseDto;
import com.ticketless.resposedto.ResponseEntity;
import com.ticketless.service.PlaceService;

@RestController
public class PlaceController {

    @Autowired
    private PlaceService placeService;
    
    @GetMapping(value = "/place/search/{pageno}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getPlaceByStateAndCity(@RequestBody StateCitySearch stateCitySearch,@PathVariable("pageno") int pageNo) {
        ResponseEntity entity = new ResponseEntity();
        List<PlaceResponseDto> placeResponseDtos = placeService.getAllPlaceyStateCity(stateCitySearch,pageNo);    
        entity.setData(placeResponseDtos);
        entity.setHttpStatus(HttpStatus.OK);
        entity.setStatus("success");
        return entity;
    }
    
    @GetMapping(value = "/place/{placeId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getPlaceData(@PathVariable("placeId") Long placeId) {
        ResponseEntity responseEntity = new ResponseEntity();
        PlaceResponseDto dto = placeService.getPlaceById(placeId);
        responseEntity.setData(dto);
        responseEntity.setHttpStatus(HttpStatus.OK);
        responseEntity.setStatus("success");
        return responseEntity;
    }
    
}





























